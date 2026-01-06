package me.jing.kecheng.fra;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import me.jing.kecheng.R;
import me.jing.kecheng.utils.db.neixunDB.NeixunDbHelper;
import me.jing.kecheng.utils.files.FileUtils;
import me.jing.kecheng.utils.http.ConcurrentHttpFetcher;
import me.jing.kecheng.utils.lists.neixunItem;
import me.jing.kecheng.utils.times.TimeTools;


public class neixunFragment extends Fragment {
    private static final String TAG = "内训课"; // 日志标签
    private static final String BASE_URL = "https://kd-live.foretech.cn/webcast/api/queryVideoById";
    private static final int START_ID = 10000999;
    private static final int END_ID = 10000000; // 包含此 ID
    private File dataFile;
    // 用于暂存有效结果：key = id, value = 格式化内容
    private final Map<Integer, String> resultMap = new ConcurrentHashMap<>();
    private NeixunDbHelper neixunDbHelper;
    //listview
    private ListView neixun_listview;
    private List<neixunItem> videoList;
    private ArrayAdapter<neixunItem> adapter;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e(TAG, "onCreateView");
        // Inflate the layout for this fragment
        // 假设你有一个按钮触发请求（或直接自动触发）
        // 这里以自动触发为例（你也可以绑定到按钮点击）
        // 初始化文件

        return inflater.inflate(R.layout.fragment_neixun, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        neixunDbHelper=new NeixunDbHelper(requireContext());
        Button neixun_btn_getVideo = view.findViewById(R.id.neixun_btn_getVideo);
        neixun_listview=view.findViewById(R.id.neixun_listview);
        //点击按钮进行抓取视频
        neixun_btn_getVideo.setOnClickListener(v -> {
            initDataFile(requireActivity());
            startFetch();
        });

        //listview操作
        addList();

    }



    private void startFetch() {
        Toast.makeText(getContext(), "开始抓取内训课...", Toast.LENGTH_SHORT).show();
        // 构建所有要请求的 URL（从大到小）
        List<String> urls = new ArrayList<>();
        Map<String, Integer> urlToIdMap = new HashMap<>(); // 反查 ID
        // 构造 URL 并记录 ID（从大到小）
        for (int id = START_ID; id >= END_ID; id--) {
            String url = BASE_URL + "?id=" + id;
            urls.add(url);
            urlToIdMap.put(url, id);
        }
        resultMap.clear(); // 清空上次结果
        // 配置 headers（如需，当前接口可能不需要，但保留一致性）
        Map<String, String> headers = new HashMap<>();
        // 如果接口需要 token 等，可在此添加
        // headers.put("token", "...");

        ConcurrentHttpFetcher fetcher = new ConcurrentHttpFetcher();
        fetcher.setDefaultHeaders(headers);
        fetcher.fetchConcurrentGet(
                urls,
                (url, responseBody) -> {
                    Integer id = urlToIdMap.get(url);
                    //Log.e("解析视频",responseBody);
                    if (id != null) {
                        parseAndStore(id, responseBody); // ← 存入 resultMap，不写文件
                    }
                },
                (url, errorMsg) -> {
                    Log.e(TAG, "请求失败: " + url + " | " + errorMsg);
                },
                (success, failure) -> {
                    writeResultsInOrder();
                    new Handler(Looper.getMainLooper()).post(() -> {
                        String msg = String.format("抓取完成！成功: %d, 失败: %d", success, failure);
                        Toast.makeText(getContext(), msg, Toast.LENGTH_LONG).show();
                        Log.e(TAG, msg);
                    });
                }
        );



    }
    // 新方法：解析并存储到 resultMap
    private void parseAndStore(int id, String body) {
        try {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(body, JsonObject.class);

            if (!root.has("result") || root.get("result").isJsonNull()) {
                return;
            }

            JsonObject result = root.getAsJsonObject("result");
            if (!result.has("name") || !result.has("url")) {
                return;
            }

            String name = result.get("name").getAsString();
            String url = result.get("url").getAsString();
            int duration = Integer.parseInt(result.get("duration").getAsString());
            String createTime = result.get("createTime").getAsString();
            String videoId = result.get("id").getAsString();


            if (!name.contains("内训课")) {
                return;
            }

            String content = String.format("名称: %s\n链接: %s\n\n", name, url);
            resultMap.put(id, content); // ✅ 存起来，等最后统一写

            // ✅ 插入或替换到数据库（videoId 为主键）
            SQLiteDatabase db = neixunDbHelper.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(NeixunDbHelper.COLUMN_VIDEOID, videoId);
            values.put(NeixunDbHelper.COLUMN_NAME, name);
            values.put(NeixunDbHelper.COLUMN_DURATION, TimeTools.secondsToHHMMSS(duration));
            values.put(NeixunDbHelper.COLUMN_URL, url);
            values.put(NeixunDbHelper.COLUMN_CREATETIME, createTime);
// 使用 CONFLICT_REPLACE 实现“存在则更新，否则插入”
            long rowId = db.insertWithOnConflict(
                    NeixunDbHelper.TABLE_NEIXUN,
                    null,
                    values,
                    SQLiteDatabase.CONFLICT_REPLACE
            );

            if (rowId == -1) {
                Log.e(TAG, "数据库插入失败 ID=" + id);
            } else {
                Log.d(TAG, "成功保存视频 ID=" + id);
            }
            db.close();


        } catch (Exception e) {
            Log.e(TAG, "解析失败 ID=" + id, e);
        }
    }
    private void writeResultsInOrder() {
        if (dataFile == null) return;

        // 将 resultMap 转为列表并按 ID 降序排序
        List<Map.Entry<Integer, String>> sortedList = new ArrayList<>(resultMap.entrySet());
        sortedList.sort((a, b) -> Integer.compare(b.getKey(), a.getKey())); // 降序：大 → 小

        // 拼接所有内容
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : sortedList) {
            sb.append(entry.getValue());
        }

        // 追加写入（文件头已在 initDataFile 中写好）
        FileUtils.writeToFile(dataFile, sb.toString(), true);
    }
    private void initDataFile(Context context) {
        File externalFilesDir = context.getExternalFilesDir(null);
        if (externalFilesDir != null) {
            dataFile = new File(externalFilesDir, "内训课.txt");

            // 删除旧文件（如果存在）
            if (dataFile.exists()) {
                boolean deleted = dataFile.delete();
                Log.e("File", "删除旧文件: " + (deleted ? "成功" : "失败"));
            }

            // 创建文件头
            String header = "=== 视频数据记录 ===\n" +
                    "生成时间: " + getCurrentTime() + "\n" +
                    "==================\n\n";
            FileUtils.writeToFile(dataFile, header, true);

            Log.e("File", "文件路径: " + dataFile.getAbsolutePath());
        }
    }
    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }
    //---------------------------------------------------------------
    private void addList() {
        // 初始化空列表和适配器
        videoList = new ArrayList<>();
        adapter= new ArrayAdapter<>(requireContext(), R.layout.item_neixun, videoList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = convertView;
                if (view == null) {
                    view = LayoutInflater.from(getContext()).inflate(R.layout.item_neixun, parent, false);
                }
                neixunItem item = getItem(position);
                if (item != null) {
                    TextView tvName = view.findViewById(R.id.neixun_name);
                    TextView tvDuration = view.findViewById(R.id.neixun_duration);
                    TextView tvTime = view.findViewById(R.id.neixun_createTime);

                    tvName.setText(item.name);
                    tvDuration.setText(item.duration);
                    tvTime.setText(item.createTime);
                }

                return view;
            }
        };
        neixun_listview.setAdapter(adapter);

        // 首次加载已有数据
        loadVideosFromDatabase();
    }

    private void loadVideosFromDatabase() {
        new Thread(() -> {
            SQLiteDatabase db = neixunDbHelper.getReadableDatabase();
            List<neixunItem> list = NeixunDbHelper.getAllNeixunVideos(db);
            db.close();

            // 切回主线程更新 UI
            new Handler(Looper.getMainLooper()).post(() -> {
                videoList.clear();
                videoList.addAll(list);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }

}