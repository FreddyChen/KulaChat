package com.freddy.kulachat.database.greendao;

import android.database.sqlite.SQLiteDatabase;

import com.freddy.kulachat.KulaApp;
import com.freddy.kulachat.model.user.UserManager;

public class GreenDaoDatabaseHelper {

    private DaoSession daoSession;

    private GreenDaoDatabaseHelper() {
    }

    public static GreenDaoDatabaseHelper getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final GreenDaoDatabaseHelper INSTANCE = new GreenDaoDatabaseHelper();
    }

    public void init() {
        Long userId = UserManager.getInstance().getUserId();
        if(userId == null) return;
        CDevOpenHelper helper = new CDevOpenHelper(KulaApp.getInstance(), userId + ".db");
        SQLiteDatabase db = helper.getWritableDatabase();
        DaoMaster master = new DaoMaster(db);
        daoSession = master.newSession();
    }

    public UserDao getUserDao() {
        checkInit();
        if (daoSession != null) return daoSession.getUserDao();

        return null;
    }

    private void checkInit() {
        if (daoSession == null) {
            init();
        }
    }

    public void release() {
        if (daoSession != null) {
            daoSession.clear();
        }
        daoSession = null;
    }
}
