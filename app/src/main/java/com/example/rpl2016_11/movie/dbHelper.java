package com.example.rpl2016_11.movie;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class dbHelper extends SQLiteOpenHelper {

    public static final int db_version =1;
    public static final String db_name = "db_fav";

    public static final String tabel_nama = "fav";

    public static final String kolom_id = "id";
    public static final String kolom_title = "title";
    public static final String kolom_thumbnail = "imageUrl";
    public static final String kolom_rate = "rate";
    public static final String kolom_desc = "description";
    public static final String kolom_language = "language";
    public static final String kolom_popularity = "popularity";
    public static final String kolom_date = "date";


    String buat_tabel = "CREATE TABLE " + tabel_nama + "(" +
            kolom_id + " INTEGER PRIMARY KEY, " +
            kolom_thumbnail + " TEXT, " +
            kolom_title + " TEXT, " +
            kolom_desc + " TEXT, " +
            kolom_popularity + " TEXT, " +
            kolom_date + " TEXT, " +
            kolom_language + " TEXT, " +
            kolom_rate + " TEXT " + ")";

    public dbHelper(Context context) {

        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(buat_tabel);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int old_ver, int new_ver) {
        db.execSQL("DROP TABLE IF EXITS" + tabel_nama);
    }

    public long addToDB(nowItem m){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(kolom_title, m.getTittle());
        values.put(kolom_thumbnail, m.getImageurl());
        values.put(kolom_desc, m.getDescription());
        values.put(kolom_language, m.getLanguage());
        values.put(kolom_popularity, m.getPopularity());
        values.put(kolom_date, m.getDate());
        values.put(kolom_rate, m.getRate());

        long id = db.insert(tabel_nama, null, values);
        db.close();
        return id;
    }

    public nowItem read(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor =db.query(tabel_nama,
                new String[]{kolom_id, kolom_title, kolom_thumbnail, kolom_desc, kolom_language, kolom_popularity, kolom_date, kolom_rate},
                kolom_id + "=?",
                new String[]{String.valueOf(id)}, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        nowItem m = new nowItem(
                cursor.getString(cursor.getColumnIndex(kolom_title)),
                cursor.getString(cursor.getColumnIndex(kolom_thumbnail)),
                cursor.getString(cursor.getColumnIndex(kolom_desc)),
                cursor.getString(cursor.getColumnIndex(kolom_language)),
                cursor.getString(cursor.getColumnIndex(kolom_popularity)),
                cursor.getString(cursor.getColumnIndex(kolom_date)),
                cursor.getString(cursor.getColumnIndex(kolom_rate)));
        cursor.close();

        return m;
    }

    public List<nowItem> readAll(){
        List<nowItem> data = new ArrayList<>();
        String query = "select * from " + tabel_nama;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor!= null)
            cursor.moveToFirst();

        if (cursor.moveToFirst()){
            do {
                nowItem m = new nowItem(
                        cursor.getString(cursor.getColumnIndex(kolom_title)),
                        cursor.getString(cursor.getColumnIndex(kolom_thumbnail)),
                        cursor.getString(cursor.getColumnIndex(kolom_desc)),
                        cursor.getString(cursor.getColumnIndex(kolom_language)),
                        cursor.getString(cursor.getColumnIndex(kolom_popularity)),
                        cursor.getString(cursor.getColumnIndex(kolom_date)),
                        cursor.getString(cursor.getColumnIndex(kolom_rate)));
                data.add(m);
            }while (cursor.moveToNext());
        }
        db.close();
        return data;
    }

   /* public void delete(nowItem data){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(tabel_nama, kolom_id + "=?",
                new String[]{String.valueOf(data.getId())});

        db.close();

    }*/
}
