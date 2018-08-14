package com.gsh.mailservice.enums;

/**
 * 通用返回结果
 */
public enum ResultEnum {

    //成功
    SUCCESS(200,"success"),

    //用户ID为空
    USER_ID_ERROR(10007,"用户id为空"),

    //报警模块为空
    MODULE_ERROR(10008,"告警模块为空"),

    //报警等级为空
    LEVEL_NULL_ERROR(10009,"告警等级为空"),

    //告警地址为空
    ADDRESS_ERROR(10010,"告警地址为空"),

    //告警主题为空
    SUBJECT_ERROR(10011,"告警主题为空"),

    //告警内容为空
    CONTENT_ERROR(10012,"告警内容为空"),

    //告警ID为空
    ALERT_ID_ERROR(10013, "告警ID为空"),

    //时间间隔为空
    INTERVAL_NULL_ERROR(10014, "时间间隔为空"),

    //告警方式为空
    TYPE_NULL_ERROR(10015,"告警方式为空"),

    //策略插入失败
    RULES_INSERT_ERROR(10016,"策略插入失败"),

    //数据库中没有这条策略
    RULES_NULL_ERROR(10017, "没有这条策略数据"),

    //策略方式为空
    PATTERN_NULL_ERROR(10018, "策略方式为空"),

    //告警消息为空
    ALERT_NULL_ERROR(10019, "告警消息为空"),

    //系统错误
    SYSTEM_ERROR(-10001,"系统错误");


    private Integer resultCode;
    private String resultMsg;

    private ResultEnum(Integer resultCode, String resultMsg){
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }
}
