package com.freddy.kulachat.database.interf;

import com.freddy.kulachat.entity.User;

public interface IDatabaseInterface {

    void init();

    void saveUser(User user);

    User queryUser(Long userId);

    void release();
}
