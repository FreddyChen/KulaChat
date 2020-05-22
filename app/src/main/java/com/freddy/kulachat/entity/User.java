package com.freddy.kulachat.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;
import org.greenrobot.greendao.annotation.Generated;

/**
 * @author FreddyChen
 * @name
 * @date 2020/05/23 01:52
 * @email chenshichao@outlook.com
 * @github https://github.com/FreddyChen
 * @describe
 */
@Entity(
        nameInDb = "tb_user"
)
public class User {

    @Id(autoincrement = true)
    private Long id;

    @NotNull
    @Unique
    private String userId;

    private String userName;

@Generated(hash = 1513057349)
public User(Long id, @NotNull String userId, String userName) {
    this.id = id;
    this.userId = userId;
    this.userName = userName;
}

@Generated(hash = 586692638)
public User() {
}

public Long getId() {
    return this.id;
}

public void setId(Long id) {
    this.id = id;
}

public String getUserId() {
    return this.userId;
}

public void setUserId(String userId) {
    this.userId = userId;
}

public String getUserName() {
    return this.userName;
}

public void setUserName(String userName) {
    this.userName = userName;
}
}
