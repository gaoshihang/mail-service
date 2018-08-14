package com.gsh.mailservice.controller;

import com.gsh.mailservice.auth.manager.TokenManager;
import com.gsh.mailservice.auth.model.TokenModel;
import com.gsh.mailservice.domain.ResultDTO;
import com.gsh.mailservice.enums.ResultEnum;
import com.gsh.mailservice.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TokenController {

    @Autowired
    private TokenManager tokenManager;

    @PostMapping(value = "/tokenService/getToken")
    public ResultDTO getToken(@RequestParam("userId") String userId){

        ResultDTO resultDTO = null;
        if(userId == null || userId.equals("")){
            resultDTO = ResultUtil.result(ResultEnum.USER_ID_ERROR.getResultCode(),ResultEnum.USER_ID_ERROR.getResultMsg(),null);
            return resultDTO;
        }

        TokenModel model = tokenManager.createToken(Long.parseLong(userId));
        String token = model.getToken();

        resultDTO = ResultUtil.result(ResultEnum.SUCCESS.getResultCode(), ResultEnum.SUCCESS.getResultMsg(), token);
        return resultDTO;
    }
}
