package com.gsh.mailservice.dao;

import com.gsh.mailservice.vo.RulesVo;

/**
 * 策略表dao接口
 */
public interface RulesDao {

    //插入一条规则
    public boolean insertRules(RulesVo rulesVo);

    //通过用户id和报警id查询具体报警信息
    public RulesVo findByUserAndAlertId(Long userId, Integer alertId);

}
