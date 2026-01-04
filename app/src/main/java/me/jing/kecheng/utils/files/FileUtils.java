package me.jing.kecheng.utils.files;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class FileUtils {
    private static final String TAG = "文件工具";

    /**
     * 写入内容到指定文件
     */
    public static boolean writeToFile(File file, String content, boolean append) {
        // 确保目录存在
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            if (!parentDir.mkdirs()) {
                Log.e(TAG, "创建目录失败: " + parentDir.getAbsolutePath());
                return false;
            }
        }

        try (FileWriter fw = new FileWriter(file, append);
             BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(content);
            bw.flush();
            Log.e(TAG, "写入文件成功: " + file.getAbsolutePath());
            return true;
        } catch (IOException e) {
            Log.e(TAG, "写入文件失败: " + e.getMessage());
            return false;
        }
    }


    public static String timestampToDate(long timestamp, String pattern) {
        try {
            // 时间戳是秒级，需要乘以1000转换为毫秒
            Date date = new Date(timestamp * 1000L);
            SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.getDefault());
            return sdf.format(date);
        } catch (Exception e) {
            return "时间转换失败";
        }
    }



}
