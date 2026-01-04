package me.jing.kecheng.utils.db.gupaiDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class GupaiDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "gupai.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * neixun参数：    name名称,  duration时长（秒）,  url链接,  createTime创建时间，  videoId视频id
     * gupai参数：title名称,endTime-startTime时长,videoUrl链接,startTime创建时间，videoId视频id
     */
    // 表名和列名
    public static final String TABLE_GUPAI  = "gupai";
    //public static final String COLUMN_ID = "id";//主键

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DURATION = "duration";
    public static final String COLUMN_URL = "url";
    public static final String COLUMN_CREATETIME = "createTime";
    public static final String COLUMN_VIDEOID = "videoId";


    public GupaiDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_GUPAI + " (" +
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
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GUPAI);
        onCreate(db);
    }
}
