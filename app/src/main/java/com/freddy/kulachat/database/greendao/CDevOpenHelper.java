package com.freddy.kulachat.database.greendao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import org.greenrobot.greendao.database.Database;

public class CDevOpenHelper extends DaoMaster.OpenHelper {

    public CDevOpenHelper(Context context, String name) {
        super(context, name);
    }

    public CDevOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        DaoMaster.dropAllTables(db, true);
        onCreate(db);
    }
}
