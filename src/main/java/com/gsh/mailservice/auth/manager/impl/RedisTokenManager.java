package com.gsh.mailservice.auth.manager.impl;

import com.gsh.mailservice.auth.manager.TokenManager;
import com.gsh.mailservice.auth.model.TokenModel;
import com.gsh.mailservice.util.JedisUtil;
import com.gsh.mailservice.util.ObjectUtil;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * 通过Redis存储和验证token的实现类
 */
@Component
public class RedisTokenManager implements TokenManager {

    public TokenModel createToken(long userId){
        //使用uuid作为源token
        String token = UUID.randomUUID().toString().replace("-","");
        TokenModel model = new TokenModel(userId,token);
        //存储到redis
        try {
            JedisUtil.set(String.valueOf(userId), token);
        }catch (Exception e){
            e.printStackTrace();
        }
        return model;
    }

    public TokenModel getToken(String authentication){
        if(authentication == null || authentication.length() == 0){
            return null;
        }
        String[] param = authentication.split("_");
        if(param.length != 2){
            return null;
        }
        //使用userId和源token简单拼接成的token
        long userId = Long.parseLong(param[0]);
        String token = param[1];
        return new TokenModel(userId, token);
    }

    public boolean checkToken(TokenModel model){
        if(model == null){
            return false;
        }
        String token = JedisUtil.get(String.valueOf(model.getUserId()));
        if(token == null || !token.equals(model.getToken())){
            return false;
        }
        return true;
    }

    public void deleteToken(long userId){
        try {
            JedisUtil.del(ObjectUtil.object2Bytes(userId));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
