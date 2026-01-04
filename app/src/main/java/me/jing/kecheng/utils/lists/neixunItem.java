package me.jing.kecheng.utils.lists;

public class neixunItem {
    public final String videoId;
    public final String name;
    public final String duration; // 秒
    public final String url;
    public final String createTime;

    public neixunItem(String videoId, String name, String duration, String url, String createTime) {
        this.videoId = videoId;
        this.name = name;
        this.duration = duration;
        this.url = url;
        this.createTime = createTime;
    }
}
