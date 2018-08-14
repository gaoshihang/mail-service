package com.gsh.mailservice.util;

import com.gsh.mailservice.domain.ResultDTO;
import com.gsh.mailservice.enums.ResultEnum;
import com.gsh.mailservice.vo.AlertVo;
import com.gsh.mailservice.vo.RulesVo;

/**
 * 空值校验类
 */
public class NullUtil {

    /**
     * 对策略进行空值校验
     * @param rulesVo
     * @return
     */
    public static ResultDTO isRulesCorrect(RulesVo rulesVo){

        ResultDTO resultDTO = null;

        //做一些空值校验
        if(rulesVo.getUserId() == null){
            resultDTO = ResultUtil.result(ResultEnum.USER_ID_ERROR.getResultCode(),ResultEnum.USER_ID_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getAlertId() == null){
            resultDTO = ResultUtil.result(ResultEnum.ALERT_ID_ERROR.getResultCode(),ResultEnum.ALERT_ID_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getModule() == null || rulesVo.getModule().equals("")){
            resultDTO = ResultUtil.result(ResultEnum.MODULE_ERROR.getResultCode(),ResultEnum.MODULE_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getSubject() == null || rulesVo.getSubject().equals("")){
            resultDTO = ResultUtil.result(ResultEnum.SUBJECT_ERROR.getResultCode(),ResultEnum.SUBJECT_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getLevel() == null || rulesVo.getLevel().equals("")){
            resultDTO = ResultUtil.result(ResultEnum.LEVEL_NULL_ERROR.getResultCode(),ResultEnum.LEVEL_NULL_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getPattern() == null){
            resultDTO = ResultUtil.result(ResultEnum.PATTERN_NULL_ERROR.getResultCode(),ResultEnum.PATTERN_NULL_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(rulesVo.getType()== null){
            resultDTO = ResultUtil.result(ResultEnum.TYPE_NULL_ERROR.getResultCode(),ResultEnum.TYPE_NULL_ERROR.getResultMsg(),null);
            return resultDTO;
        }

        resultDTO = ResultUtil.result(ResultEnum.SUCCESS.getResultCode(), ResultEnum.SUCCESS.getResultMsg(), null);

        return resultDTO;
    }


    /**
     * 对告警消息进行空值校验
     * @param alertVo
     * @return
     */
    public static ResultDTO isAlertCorrect(AlertVo alertVo){

        ResultDTO resultDTO = null;

        //做一些空值校验
        if(alertVo.getUserId()== null){
            resultDTO = ResultUtil.result(ResultEnum.USER_ID_ERROR.getResultCode(),ResultEnum.USER_ID_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(alertVo.getAlertId() == null){
            resultDTO = ResultUtil.result(ResultEnum.ALERT_ID_ERROR.getResultCode(),ResultEnum.ALERT_ID_ERROR.getResultMsg(),null);
            return resultDTO;
        }
        if(alertVo.getAddress() == null){
            resultDTO = ResultUtil.result(ResultEnum.ADDRESS_ERROR.getResultCode(),ResultEnum.ADDRESS_ERROR.getResultMsg(),null);
            return resultDTO;
        }

        resultDTO = ResultUtil.result(ResultEnum.SUCCESS.getResultCode(), ResultEnum.SUCCESS.getResultMsg(), null);

        return resultDTO;
    }
}
