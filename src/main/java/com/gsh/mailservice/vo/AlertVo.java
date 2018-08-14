package com.gsh.mailservice.vo;

import com.gsh.mailservice.enums.LevelEnum;

import java.io.Serializable;

/**
 * 通用消息类
 */
public class AlertVo implements Serializable {

    //用户Id
    private Long userId;

    //策略Id
    private Integer alertId;

    //报警地址
    private String address;

    //报警主题
    private String subject;

    //报警内容
    private String content;

    public AlertVo(Long userId, Integer alertId, String address, String content) {
        this.userId = userId;
        this.alertId = alertId;
        this.address = address;
        this.content = content;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
