package com.gsh.mailservice.consumer;

import com.gsh.mailservice.dao.RulesDao;
import com.gsh.mailservice.enums.LevelEnum;
import com.gsh.mailservice.util.JedisUtil;
import com.gsh.mailservice.util.MailUtil;
import com.gsh.mailservice.util.ObjectUtil;
import com.gsh.mailservice.util.TextUtil;
import com.gsh.mailservice.vo.AlertVo;
import com.gsh.mailservice.vo.RulesVo;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 告警消息消费者
 */
@Component
public class alertConsumer implements DisposableBean,Runnable{

    @Autowired
    private RulesDao rulesDao;

    private Thread thread;
    private volatile boolean someCondition = true;

    alertConsumer(){
        this.thread = new Thread(this);
        this.thread.start();
    }

    @Override
    public void run(){
        while(someCondition){
            long length = JedisUtil.llen("alert".getBytes());
            System.out.println("alertLength ===" + length);
            try {
                if(length == 0){
                    Thread.sleep(1000);
                    continue;
                }
                byte[] value = JedisUtil.rpop("alert".getBytes());
                if(value != null && value.length != 0){
                    AlertVo alertVo = (AlertVo) ObjectUtil.bytes2Object(value);
                    long userId = alertVo.getUserId();
                    int alertId = alertVo.getAlertId();

                    //用userId及alertId得到策略
                    RulesVo rulesVo = rulesDao.findByUserAndAlertId(userId, alertId);
                    //pattern为0时使用次数限制，为1时使用时间限制
                    int pattern = rulesVo.getPattern();
                    //type为0时使用邮件发送，为1时使用短信发送
                    int type = rulesVo.getType();
                    String subject = rulesVo.getSubject();
                    int frequency = rulesVo.getFrequency();
                    int interval = rulesVo.getInterval();

                    alertVo.setSubject(subject);

                    String key = userId + ";" + alertId;

                    if(pattern == 0){

                        if(JedisUtil.get(key) == null){
                            JedisUtil.set(key, "0");
                        }

                        if(Integer.parseInt(JedisUtil.get(key)) < frequency){
                            JedisUtil.incr(key);
                            System.out.println("还没有到达发送次数");
                        }else{
                            if(type == 0){
                                MailUtil.sendHtmlMail(alertVo);
                            }else if(type == 1){
                                TextUtil.sendText(alertVo);
                            }
                            JedisUtil.del(key);
                        }

                    }else if(pattern == 1){
                        if(JedisUtil.hasKey(key)){
                            System.out.println("请不要重复发送");
                        }else{
                            if(type == 0){
                                MailUtil.sendHtmlMail(alertVo);
                            }else if(type == 1){
                                TextUtil.sendText(alertVo);
                            }
                            JedisUtil.set(key, "1", interval);
                        }
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void destroy(){
        someCondition = false;
    }
}
