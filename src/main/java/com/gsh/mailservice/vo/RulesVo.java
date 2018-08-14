package com.gsh.mailservice.vo;

import com.gsh.mailservice.enums.LevelEnum;

import java.io.Serializable;

/**
 * 规则实体类
 */
public class RulesVo implements Serializable {

    //用户Id
    private Long userId;

    //策略id
    private Integer alertId;

    //告警模块
    private String module;

    //告警主题
    private String subject;

    //告警级别
    private LevelEnum level;

    //告警方式，邮件为0，短信为1
    private Integer pattern;

    //策略方式，0为多久发一次，1为被调用多少次发一次
    private Integer type;

    //告警次数
    private Integer frequency;

    //告警间隔,秒计
    private Integer interval;


    public RulesVo() {

    }

    public RulesVo(Long userId, Integer alertId, String module, String subject, LevelEnum level, Integer pattern, Integer type, Integer frequency, Integer interval) {
        this.userId = userId;
        this.alertId = alertId;
        this.module = module;
        this.subject = subject;
        this.level = level;
        this.pattern = pattern;
        this.type = type;
        this.frequency = frequency;
        this.interval = interval;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getAlertId() {
        return alertId;
    }

    public void setAlertId(Integer alertId) {
        this.alertId = alertId;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public LevelEnum getLevel() {
        return level;
    }

    public void setLevel(LevelEnum level) {
        this.level = level;
    }

    public Integer getPattern() {
        return pattern;
    }

    public void setPattern(Integer pattern) {
        this.pattern = pattern;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getInterval() {
        return interval;
    }

    public void setInterval(Integer interval) {
        this.interval = interval;
    }
}
