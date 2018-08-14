package com.gsh.mailservice.controller;

import com.gsh.mailservice.dao.RulesDao;
import com.gsh.mailservice.domain.ResultDTO;
import com.gsh.mailservice.enums.LevelEnum;
import com.gsh.mailservice.enums.ResultEnum;
import com.gsh.mailservice.util.NullUtil;
import com.gsh.mailservice.util.ResultUtil;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 告警策略接收controller
 */
@RestController
public class AlertRulesController {

    @Autowired
    private RulesDao rulesDao;

    /**
     * 接收前台告警策略并存入mysql中
     * @param userId
     * @param alertId
     * @param module
     * @param subject
     * @param level
     * @param pattern
     * @param type
     * @param frequency
     * @param interval
     * @return
     */
    @PostMapping(value = "/alertRules/saveAlertRules")
    public ResultDTO saveAlertRules(@RequestParam("userId") Long userId, @RequestParam("alertId") Integer alertId, @RequestParam("module") String module,
                                    @RequestParam("subject") String subject, @RequestParam("level") LevelEnum level, @RequestParam("pattern") Integer pattern,
                                    @RequestParam("type") Integer type, @RequestParam(required = false, defaultValue = "0", value = "frequency") Integer frequency,
                                    @RequestParam(required = false, defaultValue = "0", value = "interval") Integer interval){

        RulesVo rulesVo = new RulesVo(userId, alertId, module, subject, level, pattern, type, frequency, interval);

        ResultDTO resultDTO = NullUtil.isRulesCorrect(rulesVo);

        if(resultDTO.getCode() == 200){
            boolean result = rulesDao.insertRules(rulesVo);
            if(result == false){
                resultDTO = ResultUtil.result(ResultEnum.RULES_INSERT_ERROR.getResultCode(),ResultEnum.RULES_INSERT_ERROR.getResultMsg(),null);
                return resultDTO;
            }
        }


        return resultDTO;
    }

}
