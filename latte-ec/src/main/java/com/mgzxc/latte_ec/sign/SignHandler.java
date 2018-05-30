package com.mgzxc.latte_ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mgzxc.latte_ec.database.DatabaseManager;
import com.mgzxc.latte_ec.database.UserProfile;

/**
 * Created by MG_ZXC on 2018/5/30.
 */
public class SignHandler {
    public static void onSignIn(String response){
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avator = profileJson.getString("avator");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");
        final UserProfile profile = new UserProfile(userId, name, avator, gender, address);
        DatabaseManager.getInstance().getDao().insert(profile);

    }
}
