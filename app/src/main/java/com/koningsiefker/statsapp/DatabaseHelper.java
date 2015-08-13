package com.koningsiefker.statsapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Brendan on 8/12/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DatabaseContract.TableOne.CREATE_QUERY);
        sqLiteDatabase.execSQL(DatabaseContract.TableTwo.CREATE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL(DatabaseContract.TableOne.DROP_QUERY);
        sqLiteDatabase.execSQL(DatabaseContract.TableTwo.DROP_QUERY);
        onCreate(sqLiteDatabase);
    }
}
