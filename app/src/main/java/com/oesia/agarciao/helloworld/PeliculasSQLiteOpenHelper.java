package com.oesia.agarciao.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alberto on 15/09/2015.
 */
public class PeliculasSQLiteOpenHelper extends SQLiteOpenHelper {

    private Context context;

    public PeliculasSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        for (String statement : this.context.getResources().getStringArray(R.array.script_create)) {
            db.execSQL(statement);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
