package com.freddy.kulachat.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Unique;

import java.util.Objects;

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
    private Long userId;
    private String phone;
    private String nickname;
    private String avatar;
    private int gender;
    private String birthday;
    private String signature;
    private String province;
    private String city;
    private String area;
    private int completedInfo;

    public static final int GENDER_UNKNOWN = 0;
    public static final int GENDER_MALE = 1;
    public static final int GENDER_FEMALE = 2;

    private static final int IS_COMPLETED_INFO = 1;
    private static final int UN_COMPLETED_INFO = 0;

    @Generated(hash = 1144158524)
    public User(Long id, @NotNull Long userId, String phone, String nickname,
                String avatar, int gender, String birthday, String signature,
                String province, String city, String area, int completedInfo) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.nickname = nickname;
        this.avatar = avatar;
        this.gender = gender;
        this.birthday = birthday;
        this.signature = signature;
        this.province = province;
        this.city = city;
        this.area = area;
        this.completedInfo = completedInfo;
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

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getGender() {
        return this.gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return this.signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public int getCompletedInfo() {
        return this.completedInfo;
    }

    public void setCompletedInfo(int completedInfo) {
        this.completedInfo = completedInfo;
    }

    public boolean isCompletedInfo() {
        return this.completedInfo == IS_COMPLETED_INFO;
    }

    public void setCompletedInfo(boolean completedInfo) {
        setCompletedInfo(completedInfo ? IS_COMPLETED_INFO : UN_COMPLETED_INFO);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId=" + userId +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", avatar='" + avatar + '\'' +
                ", gender=" + gender +
                ", birthday='" + birthday + '\'' +
                ", signature='" + signature + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", completedInfo=" + completedInfo +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }
}
