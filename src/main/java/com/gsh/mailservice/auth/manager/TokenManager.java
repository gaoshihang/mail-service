package com.gsh.mailservice.auth.manager;

import com.gsh.mailservice.auth.model.TokenModel;

/**
 * 对Token进行操作的接口
 */
public interface TokenManager {
    /**
     * 创建一个token关联上指定用户
     * @param userId
     * @return
     */
    public TokenModel createToken(long userId);

    /**
     * 检查token是否有效
     * @param model
     * @return
     */
    public boolean checkToken(TokenModel model);

    /**
     * 从字符串中解析token
     * @param authentication
     * @return
     */
    public TokenModel getToken(String authentication);

    /**
     * 清除token
     * @param userId
     */
    public void deleteToken(long userId);
}
