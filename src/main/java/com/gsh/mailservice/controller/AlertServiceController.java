package com.gsh.mailservice.controller;

import com.gsh.mailservice.dao.RulesDao;
import com.gsh.mailservice.domain.ResultDTO;
import com.gsh.mailservice.enums.LevelEnum;
import com.gsh.mailservice.enums.ResultEnum;
import com.gsh.mailservice.util.JedisUtil;
import com.gsh.mailservice.util.NullUtil;
import com.gsh.mailservice.util.ObjectUtil;
import com.gsh.mailservice.util.ResultUtil;
import com.gsh.mailservice.vo.AlertVo;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 告警消息接收controller
 */
@RestController
public class AlertServiceController {

    @Autowired
    private RulesDao rulesDao;

    /**
     * 报警信息进入redis队列
     */
    @PostMapping(value = "/alertService/sendAlert")
    public ResultDTO alertToQueue(@RequestParam("userId") Long userId, @RequestParam("alertId") Integer alertId, @RequestParam("address") String address,
                                  @RequestParam("content") String content)
            throws Exception{

        AlertVo alertVo = new AlertVo(userId, alertId, address, content);

        ResultDTO resultDTO = NullUtil.isAlertCorrect(alertVo);

        if(resultDTO.getCode() == 200){
            if(alertVo != null){
                JedisUtil.lpush("alert".getBytes(),ObjectUtil.object2Bytes(alertVo));
            }else{
                resultDTO = ResultUtil.result(ResultEnum.ALERT_NULL_ERROR.getResultCode(),ResultEnum.ALERT_NULL_ERROR.getResultMsg(),null);
            }

        }

        return resultDTO;
    }
}
