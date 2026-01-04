package me.jing.kecheng.utils.http;

import android.os.Handler;
import android.os.Looper;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 通用并发 HTTP 请求工具类（支持 GET / POST）
 * 适用于批量请求同一接口（如 /api/xxx?id=1, /api/xxx?id=2...）
 */
public class ConcurrentHttpFetcher {

    private final OkHttpClient client;
    private final Gson gson;
    private final ExecutorService executor;
    private final Handler mainHandler = new Handler(Looper.getMainLooper());

    // 默认 Header（可全局设置）
    private Map<String, String> defaultHeaders;
    private MediaType defaultMediaType = MediaType.get("application/json; charset=utf-8");

    public ConcurrentHttpFetcher() {
        this.client = new OkHttpClient.Builder()
                .connectTimeout(10, java.util.concurrent.TimeUnit.SECONDS)
                .readTimeout(30, java.util.concurrent.TimeUnit.SECONDS)
                .build();
        this.gson = new Gson();
        this.executor = Executors.newCachedThreadPool();
    }

    /**
     * 设置默认 Header（如 token、User-Agent 等）
     */
    public void setDefaultHeaders(Map<String, String> headers) {
        this.defaultHeaders = headers;
    }

    /**
     * 执行并发 GET 请求
     *
     * @param urls           要请求的 URL 列表
     * @param onSuccess      单个请求成功回调（在主线程）
     * @param onFailure      单个请求失败回调（在主线程）
     * @param onCompleteAll  所有请求完成回调（无论成功失败，在主线程）
     */
    public void fetchConcurrentGet(
            @NonNull List<String> urls,
            @Nullable OnSuccessCallback onSuccess,
            @Nullable OnFailureCallback onFailure,
            @Nullable OnCompleteAllCallback onCompleteAll) {

        if (urls.isEmpty()) {
            if (onCompleteAll != null) {
                postToMainThread(() -> onCompleteAll.onComplete(0, 0));
            }
            return;
        }

        CountDownLatch latch = new CountDownLatch(urls.size());
        List<String> successes = Collections.synchronizedList(new ArrayList<>());
        List<String> failures = Collections.synchronizedList(new ArrayList<>());

        for (String url : urls) {
            Request.Builder builder = new Request.Builder().url(url);
            applyDefaultHeaders(builder);

            client.newCall(builder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    failures.add(url);
                    if (onFailure != null) {
                        postToMainThread(() -> onFailure.onFailure(url, e.getMessage()));
                    }
                    latch.countDown();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            String body = response.body().string();
                            successes.add(body);
                            if (onSuccess != null) {
                                postToMainThread(() -> onSuccess.onSuccess(url, body));
                            }
                        } else {
                            failures.add(url);
                            if (onFailure != null) {
                                postToMainThread(() ->
                                        onFailure.onFailure(url, "HTTP " + response.code())
                                );
                            }
                        }
                    } catch (Exception e) {
                        failures.add(url);
                        if (onFailure != null) {
                            postToMainThread(() -> onFailure.onFailure(url, e.getMessage()));
                        }
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        // 等待全部完成
        executor.execute(() -> {
            try {
                latch.await();
                final int successCount = successes.size();
                final int failureCount = failures.size();
                if (onCompleteAll != null) {
                    postToMainThread(() -> onCompleteAll.onComplete(successCount, failureCount));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    /**
     * 执行并发 POST 请求（发送相同 body 到多个 URL）
     */
    public void fetchConcurrentPost(
            @NonNull List<String> urls,
            @NonNull String jsonBody,
            @Nullable OnSuccessCallback onSuccess,
            @Nullable OnFailureCallback onFailure,
            @Nullable OnCompleteAllCallback onCompleteAll) {

        if (urls.isEmpty()) {
            if (onCompleteAll != null) {
                postToMainThread(() -> onCompleteAll.onComplete(0, 0));
            }
            return;
        }

        CountDownLatch latch = new CountDownLatch(urls.size());
        List<String> successes = Collections.synchronizedList(new ArrayList<>());
        List<String> failures = Collections.synchronizedList(new ArrayList<>());

        RequestBody body = RequestBody.create(jsonBody, defaultMediaType);

        for (String url : urls) {
            Request.Builder builder = new Request.Builder()
                    .url(url)
                    .post(body);
            applyDefaultHeaders(builder);

            client.newCall(builder.build()).enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    failures.add(url);
                    if (onFailure != null) {
                        postToMainThread(() -> onFailure.onFailure(url, e.getMessage()));
                    }
                    latch.countDown();
                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    try {
                        if (response.isSuccessful() && response.body() != null) {
                            String responseBody = response.body().string();
                            successes.add(responseBody);
                            if (onSuccess != null) {
                                postToMainThread(() -> onSuccess.onSuccess(url, responseBody));
                            }
                        } else {
                            failures.add(url);
                            if (onFailure != null) {
                                postToMainThread(() ->
                                        onFailure.onFailure(url, "HTTP " + response.code())
                                );
                            }
                        }
                    } catch (Exception e) {
                        failures.add(url);
                        if (onFailure != null) {
                            postToMainThread(() -> onFailure.onFailure(url, e.getMessage()));
                        }
                    } finally {
                        latch.countDown();
                    }
                }
            });
        }

        executor.execute(() -> {
            try {
                latch.await();
                final int successCount = successes.size();
                final int failureCount = failures.size();
                if (onCompleteAll != null) {
                    postToMainThread(() -> onCompleteAll.onComplete(successCount, failureCount));
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
    }

    // 应用默认 Header
    private void applyDefaultHeaders(Request.Builder builder) {
        if (defaultHeaders != null) {
            for (Map.Entry<String, String> entry : defaultHeaders.entrySet()) {
                builder.addHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    private void postToMainThread(Runnable runnable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            mainHandler.post(runnable);
        }
    }

    // 回调接口
    public interface OnSuccessCallback {
        @MainThread
        void onSuccess(String url, String responseBody);
    }

    public interface OnFailureCallback {
        @MainThread
        void onFailure(String url, String errorMessage);
    }

    public interface OnCompleteAllCallback {
        @MainThread
        void onComplete(int successCount, int failureCount);
    }

    // 清理资源（建议在 Activity.onDestroy 调用）
    public void shutdown() {
        executor.shutdown();
    }
}