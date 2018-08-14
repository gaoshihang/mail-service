package com.gsh.mailservice.exception;

import com.gsh.mailservice.enums.ResultEnum;

public class HandleException extends RuntimeException{

    private static final long serialVersionUID = 4474665452231795065L;

    private Integer code;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public HandleException(ResultEnum resultEnum, String detailMsg){
        super(resultEnum.getResultMsg() + detailMsg);
        this.code = resultEnum.getResultCode();
    }
}
