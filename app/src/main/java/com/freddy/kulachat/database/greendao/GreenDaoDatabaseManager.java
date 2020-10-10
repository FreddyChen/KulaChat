package com.freddy.kulachat.database.greendao;

import com.freddy.kulachat.database.interf.IDatabaseInterface;
import com.freddy.kulachat.entity.User;

public class GreenDaoDatabaseManager implements IDatabaseInterface {

    private UserDao userDao;

    private GreenDaoDatabaseManager() {
    }

    public static GreenDaoDatabaseManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        private static final GreenDaoDatabaseManager INSTANCE = new GreenDaoDatabaseManager();
    }

    @Override
    public void init() {
        GreenDaoDatabaseHelper.getInstance().init();
        userDao = GreenDaoDatabaseHelper.getInstance().getUserDao();
    }

    @Override
    public void saveUser(User user) {
        if (userDao == null || user == null) return;
        userDao.insertOrReplace(user);
    }

    @Override
    public User queryUser(Long userId) {
        if (userDao == null || userId == null) return null;
        return userDao.queryBuilder().where(UserDao.Properties.UserId.eq(userId)).build().unique();
    }

    @Override
    public void release() {
        GreenDaoDatabaseHelper.getInstance().release();
    }
}
