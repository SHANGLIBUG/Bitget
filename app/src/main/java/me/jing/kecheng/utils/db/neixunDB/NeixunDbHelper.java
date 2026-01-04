package me.jing.kecheng.utils.db.neixunDB;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import me.jing.kecheng.utils.lists.neixunItem;
import me.jing.kecheng.utils.times.TimeTools;

public class NeixunDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "neixun.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * neixun参数：    name名称,  duration时长（秒）,  url链接,  createTime创建时间，  videoId视频id
     * gupai参数：title名称,endTime-startTime时长,videoUrl链接,startTime创建时间，videoId视频id
     */
    // 表名和列名
    public static final String TABLE_NEIXUN  = "neixun";
    //public static final String COLUMN_ID = "id";//主键

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_CREATETIME = "createTime";
    public static final String COLUMN_VIDEOID = "videoId";


    public NeixunDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NEIXUN + " (" +
                COLUMN_VIDEOID + " INTEGER PRIMARY KEY, " +        // ← 主键
                COLUMN_NAME + " TEXT NOT NULL, " +
                COLUMN_DURATION + " INTEGER, " +                   // 单位：秒
                COLUMN_URL + " TEXT NOT NULL, " +
                COLUMN_CREATETIME + " TEXT" +                      // 时间戳
                ");";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEIXUN);
        onCreate(db);
    }

    public static List<neixunItem> getAllNeixunVideos(SQLiteDatabase db){
        List<neixunItem> list = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(
                    TABLE_NEIXUN,
                    null,
                    COLUMN_NAME + " LIKE ?",
                    new String[]{"%内训课%"},
                    null,
                    null,
                    COLUMN_VIDEOID + " DESC" // 降序：最新在前
            );

            while (cursor != null && cursor.moveToNext()) {
                String id = String.valueOf(cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_VIDEOID)));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                int duration = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_DURATION));
                String url = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_URL));
                String time = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CREATETIME));

                list.add(new neixunItem(id, name, TimeTools.secondsToHHMMSS(duration), url, time));
            }
        } finally {
            if (cursor != null) cursor.close();
        }
        return list;

    }
}
