package com.gsh.mailservice.util;

import com.gsh.mailservice.domain.ResultDTO;

public class ResultUtil {

    public static ResultDTO result(Integer resultCode, String resultMsg, Object result){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(resultCode);
        resultDTO.setMsg(resultMsg);
        resultDTO.setResult(result);
        return resultDTO;
    }
}
