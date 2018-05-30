package com.mgzxc.latte_ec.database;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by MG_ZXC on 2018/5/30.
 */
@Entity(nameInDb = "user_profile")
public class UserProfile {
    @Id
    private long userId = 0;
    private String name = null;
    private String avator = null;
    private String gender = null;
    private String address = null;
    @Generated(hash = 284524703)
    public UserProfile(long userId, String name, String avator, String gender,
            String address) {
        this.userId = userId;
        this.name = name;
        this.avator = avator;
        this.gender = gender;
        this.address = address;
    }
    @Generated(hash = 968487393)
    public UserProfile() {
    }
    public long getUserId() {
        return this.userId;
    }
    public void setUserId(long userId) {
        this.userId = userId;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAvator() {
        return this.avator;
    }
    public void setAvator(String avator) {
        this.avator = avator;
    }
    public String getGender() {
        return this.gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}
