package me.jing.kecheng.fra;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.jing.kecheng.R;
import me.jing.kecheng.utils.bitget.CheckSign;
import me.jing.kecheng.utils.bitget.OkHttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.TlsVersion;


public class settingFragment extends Fragment {
    private static final String TAG = "设置"; // 日志标签
/*    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(8, TimeUnit.SECONDS)   // 连接超时（建议 5~10 秒）
            .readTimeout(10, TimeUnit.SECONDS)     // 读取超时
            .writeTimeout(10, TimeUnit.SECONDS)    // 写入超时
            .build();*/
    private OkHttpClient getTls12Client() {
        // 只保留支持 TLS 1.2 的 ConnectionSpec
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2, TlsVersion.TLS_1_3)
                .build();

        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .connectionSpecs(Arrays.asList(spec))
                .build();
    }
    private OkHttpClient getStableForeignClient() {
        ConnectionSpec spec = new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS)
                .tlsVersions(TlsVersion.TLS_1_2) // 🔴 只保留 1.2
                .build();

        return new OkHttpClient.Builder()
                .connectTimeout(8, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .writeTimeout(20, TimeUnit.SECONDS)
                .protocols(Collections.singletonList(Protocol.HTTP_1_1)) // 🔴 禁用 HTTP/2
                .connectionSpecs(Collections.singletonList(spec))
                .retryOnConnectionFailure(true)
                .build();
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_setting, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //测试
        sendRequest2();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void sendRequest2() {

        try {
            String timestamp = String.valueOf(System.currentTimeMillis());
            String apiKey="bg_ed5cc372a3d4679c9f12a3c31ef29451";
            String secretKey="bbf98fc0b875a7451e000b55daa81e7acf96cfbd3d5b07b49112d74b435443b0";
            String sign = CheckSign.generate(timestamp, "GET", "/api/v2/mix/account/accounts", "productType=USDT-FUTURES", "", secretKey);


            Request request = new Request.Builder().url("https://api.bitget.com/api/v2/mix/account/accounts?productType=USDT-FUTURES")
                    .header("ACCESS-KEY", apiKey)
                    .header("ACCESS-SIGN", sign)
                    .header("ACCESS-PASSPHRASE", "12345678")
                    .header("ACCESS-TIMESTAMP", timestamp)
                    .header("locale", "zh-CN")
                    .header("Content-Type", "application/json")
                    .build();
            //OkHttpClient client = getTls12Client();
            OkHttpClient client = getStableForeignClient();

            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.e("bitget", "请求失败", e);
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    Log.e("bitget", "响应: " + response.body().string());
                }
            });
        }catch (Exception e){
            Log.e("Network", "准备请求时出错", e);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}