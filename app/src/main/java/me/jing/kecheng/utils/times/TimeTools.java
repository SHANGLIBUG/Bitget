package me.jing.kecheng.utils.times;

public class TimeTools {

    /**
     * 将秒数转换为 "HH:mm:ss" 格式
     * @param totalSeconds 总秒数
     * @return 格式化的时间字符串，如 "01:05:30"
     */
    public static String secondsToHHMMSS(int totalSeconds) {
        // 处理负数情况
        if (totalSeconds < 0) {
            return "00:00:00";
        }

        int hours = totalSeconds / 3600;
        int minutes = (totalSeconds % 3600) / 60;
        int seconds = totalSeconds % 60;

        // 使用String.format确保两位数格式
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

}
