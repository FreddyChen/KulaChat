package com.freddy.kulachat.database;

import com.freddy.kulachat.database.greendao.GreenDaoDatabaseManager;
import com.freddy.kulachat.database.interf.IDatabaseInterface;

public class DatabaseManagerFactory {

    public static IDatabaseInterface getDatabaseManager() {
        return GreenDaoDatabaseManager.getInstance();
    }
}
