package com.gsh.mailservice.util;

import com.gsh.mailservice.domain.ResultDTO;
import com.gsh.mailservice.vo.AlertVo;
import com.gsh.mailservice.vo.RulesVo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TextUtil {

    private static String OperID = "lenovoSbj";
    private static String OperPass = "x7VpE8v4";
    private static String SendTime = "";
    private static String AppendID = "";
    private static String Content_type="utf-8";
    private static String ValidTime = "";

    public static String sendText(AlertVo alertVo){

        ResultDTO resultDTO = null;

        String content = "【" + alertVo.getSubject() + "】" + alertVo.getContent();
        String DesMobile = alertVo.getAddress();

        /*将内容用URLEncoder编一次GBK*/
        String encoderContent = "";
        try {
            encoderContent = URLEncoder.encode(content, "GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        /*url地址*/
        String URL="http://221.179.172.68:8000/QxtSms/QxtFirewall";
        /*消息参数*/
        String str="OperID="+OperID+"&OperPass="+OperPass+"&SendTime="+SendTime+"&ValidTime="+ValidTime+"" +
                "&AppendID="+AppendID+"&DesMobile="+DesMobile.trim()+"&Content="+encoderContent;

        /*使用post方式发送消息*/
        String response=getURL(str, URL);

        return response;
    }


    public static String getURL(String commString, String address) {
        String rec_string = "";
        URL url = null;
        HttpURLConnection urlConn = null;
        try {
            /*得到url地址的URL类*/
            url = new URL(address+"?"+commString);
            /*获得打开需要发送的url连接*/
            urlConn = (HttpURLConnection) url.openConnection();
            /*设置连接超时时间*/
            urlConn.setConnectTimeout(30000);
            /*设置读取响应超时时间*/
            urlConn.setReadTimeout(30000);
            /*设置post发送方式*/
            urlConn.setRequestMethod("GET");
            /*发送commString*/
            urlConn.setDoInput(true);
            urlConn.setDoOutput(true);
            urlConn.connect();

            /*发送完毕 获取返回流，解析流数据*/
            BufferedReader rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "GBK"));
            StringBuffer sb = new StringBuffer();
            int ch;
            while ((ch = rd.read()) > -1) {
                sb.append((char) ch);
            }
            rec_string = sb.toString().trim();
            /*解析完毕关闭输入流*/
            rd.close();

        } catch (Exception e) {
            /*异常处理*/
            rec_string = "-107";
            System.out.println(e);
        } finally {
            if (urlConn != null) {
                /*关闭URL连接*/
                urlConn.disconnect();
            }
        }
        /*返回响应内容*/
        return rec_string;
    }
}
