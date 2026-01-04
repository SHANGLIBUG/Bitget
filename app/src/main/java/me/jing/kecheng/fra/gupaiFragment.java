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
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import me.jing.kecheng.R;
import me.jing.kecheng.utils.db.gupaiDB.GupaiDbHelper;
import me.jing.kecheng.utils.db.neixunDB.NeixunDbHelper;
import me.jing.kecheng.utils.files.FileUtils;
import me.jing.kecheng.utils.http.gupaiBean.ApiResponse;
import me.jing.kecheng.utils.http.ConcurrentHttpFetcher;
import me.jing.kecheng.utils.times.TimeTools;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class gupaiFragment extends Fragment {
    private Button gupai_btn_getVideo;
    private static final String TAG = "GuPaiFragment";
    private OkHttpClient client = new OkHttpClient();
    private File dataFile,dataFile_test;
    private boolean isFetching = false;
    // 用于线程安全写文件
    private final Object fileLock = new Object();
    private static final String URL = "https://spero-outspace.secon.cn/api/spero-ultron-service/v3/live/author/tape/spero_308591?page=1&limit=999";
    //private static final String URL = "https://www.baidu.com";
    //https://h5-hd.secon.cn/prod/pcsite/?zsldFix=0&zsldId=&empid=&agentid=&zsldCode=&uid=spero_6019900&qrfr=&ts=29398152#/live/195/158813
    private GupaiDbHelper gupaiDbHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gupai, container, false);
        // Inflate the layout for this fragment
        // 假设你有一个按钮触发请求（或直接自动触发）
        // 这里以自动触发为例（你也可以绑定到按钮点击）
        // 初始化文件

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gupaiDbHelper=new GupaiDbHelper(requireContext());
        gupai_btn_getVideo = view.findViewById(R.id.gupai_btn_getVideo);
        gupai_btn_getVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initDataFile(requireActivity());
                startFetch();
            }
        });


    }

    private void startFetch() {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "开始执行", Toast.LENGTH_LONG).show();
            }
        });
        Request request = new Request.Builder()
                .url(URL)
                .addHeader("clienttype", "android")
                .addHeader("appVersion", "8.13.11")
                .addHeader("phoneModel", "m2104k10ac")
                .addHeader("phoneBrand", "redmi")
                .addHeader("packageName", "com.spero.vision.vsnapp")
                .addHeader("marketType", "317")
                .addHeader("platform", "VISION")
                .addHeader("deviceToken", "")
                .addHeader("token", "78fd420c54f723a1fb7527cf944646e5")
                .addHeader("Host", "spero-outspace.secon.cn")
                .addHeader("Connection", "Keep-Alive")
                .addHeader("Accept-Encoding", "gzip")
                .addHeader("User-Agent", "okhttp/4.3.1")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.e("Network", "请求失败: " + e.getMessage());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful() && response.body() != null) {
                    String responseData = response.body().string();
                    //Log.e("Network", "请求成功: " + responseData);
                    Gson gson = new Gson();
                    ApiResponse apiResponse = gson.fromJson(responseData, ApiResponse.class);
                    if (apiResponse.getCode() == 0 && apiResponse.getData() != null) {
                        List<ApiResponse.DataDTO.ListDTO> list = apiResponse.getData().getList();
                        // 构造所有详情 URL
                        List<String> detailUrls = new ArrayList<>();
                        for (ApiResponse.DataDTO.ListDTO item : list) {
                            String url = "https://spero-outspace.secon.cn/api/spero-ultron-service/v2/room/195?showRoomPcShareConfig=1&tapeId=" + item.getId();
                            detailUrls.add(url);
                        }
                        // 并发请求详情
                        fetchDetailsConcurrently(detailUrls);
                    }

                }


            }
        });

    }

    private void fetchDetailsConcurrently(List<String> urls) {
        ConcurrentHttpFetcher fetcher = new ConcurrentHttpFetcher();

        Map<String, String> headers = new HashMap<>();
        headers.put("clienttype", "android");
        headers.put("appVersion", "8.13.11");
        headers.put("phoneModel", "m2104k10ac");
        headers.put("phoneBrand", "redmi");
        headers.put("packageName", "com.spero.vision.vsnapp");
        headers.put("marketType", "317");
        headers.put("platform", "VISION");
        headers.put("deviceToken", "");
        headers.put("token", "78fd420c54f723a1fb7527cf944646e5");
        headers.put("Host", "spero-outspace.secon.cn");
        headers.put("Connection", "Keep-Alive");
        headers.put("Accept-Encoding", "gzip");
        headers.put("User-Agent", "okhttp/4.3.1");
        fetcher.setDefaultHeaders(headers);

        fetcher.fetchConcurrentGet(
                urls,
                (url, responseBody) -> {
                    //FileUtils.writeToFile(dataFile_test,responseBody,false);
                    //Log.e("股拍结果", responseBody);
                    parseAndWrite(responseBody);
                },
                (url, errorMsg) -> {
                    Log.e(TAG, "详情请求失败: " + url + " | " + errorMsg);
                    // 可选：写入失败日志
                },
                (success, failure) -> {
                    new Handler(Looper.getMainLooper()).post(() -> {
                        Toast.makeText(getContext(),
                                String.format("抓取完成！成功: %d, 失败: %d", success, failure),
                                Toast.LENGTH_LONG).show();
                        //resetButton();
                        Log.e("股拍结果", String.format("抓取完成！成功: %d, 失败: %d", success, failure));
                    });
                }
        );

    }

    private void parseAndWrite(String responseBody) {
        try {
            Gson gson = new Gson();
            JsonObject root = gson.fromJson(responseBody, JsonObject.class);
            if (!root.has("data")) return;

            JsonObject data = root.getAsJsonObject("data");
            if (!data.has("tape") || data.get("tape").isJsonNull()) return;

            JsonObject tape = data.getAsJsonObject("tape");
            int id = tape.get("id").getAsInt();
            String updatedAt = tape.has("updatedAt") ? tape.get("updatedAt").getAsString() : "N/A";
            long startTime = tape.get("startTime").getAsLong();
            long endTime = tape.get("endTime").getAsLong();
            String videoUrl = tape.get("videoUrl").getAsString();
            String title = tape.get("title").getAsString();

            String startTimeStr = FileUtils.timestampToDate(startTime, "yyyy-MM-dd HH:mm");
            String endTimeStr = FileUtils.timestampToDate(endTime, "yyyy-MM-dd HH:mm");

            String content = String.format(
                    "视频ID: %d\n更新时间: %s\n开始时间: %s\n结束时间: %s\n视频链接: %s\n\n",
                    id, updatedAt, startTimeStr, endTimeStr, videoUrl
            );
            Log.e(TAG, content);
            writeToFile(content);

            // ✅ 插入或替换到数据库（videoId 为主键）
            SQLiteDatabase db = gupaiDbHelper.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(GupaiDbHelper.COLUMN_VIDEOID, id);
            values.put(GupaiDbHelper.COLUMN_NAME, title);
            values.put(GupaiDbHelper.COLUMN_DURATION, TimeTools.secondsToHHMMSS((int) (endTime-startTime)));
            values.put(GupaiDbHelper.COLUMN_URL, videoUrl);
            values.put(GupaiDbHelper.COLUMN_CREATETIME, startTimeStr);
// 使用 CONFLICT_REPLACE 实现“存在则更新，否则插入”
            long rowId = db.insertWithOnConflict(
                    GupaiDbHelper.TABLE_GUPAI,
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
            Log.e(TAG, "解析失败: " + e.getMessage());
        }
    }

    private void writeToFile(String content) {
        synchronized (fileLock) {
            if (dataFile != null) {
                FileUtils.writeToFile(dataFile, content, true);
            }
        }
    }


    private void initDataFile(Context context) {

        File externalFilesDir = context.getExternalFilesDir(null);
        //dataFile_test = new File(externalFilesDir, "股拍2222.txt");
        if (externalFilesDir != null) {
            dataFile = new File(externalFilesDir, "股拍.txt");

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


}