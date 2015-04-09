package com.jldes.amuva;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by JesúsDiego on 09/04/2015.
 */
public class SQLClasificaciones extends SQLiteOpenHelper {
    public SQLClasificaciones(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    String sqlCreate = "CREATE TABLE Clas (nombre TEXT, categoria TEXT, posicion TEXT, constructor TEXT)";

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clas");
        db.execSQL(sqlCreate);
    }
}
