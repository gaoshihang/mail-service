package com.gsh.mailservice.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 主要用于读取redis的配置信息
 */
public class Configuration extends Properties {
    private static final long serialVersionUID = -2296275030489943706L;
    private static Configuration instance = null;

    public static synchronized Configuration getInstance(){
        if(instance == null){
            instance = new Configuration();
        }
        return instance;
    }

    public String getProperty(String key,String defaultValue){
        String val = getProperty(key);
        return (val == null || val.isEmpty()) ? defaultValue : val;
    }

    public String getString (String name, String defaultValue){
        return this.getProperty(name, defaultValue);
    }

    public int getInt(String name, int defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public long getLong(String name, long defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Integer.parseInt(val);
    }

    public float getFloat(String name, float defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Float.parseFloat(val);
    }

    public double getDouble(String name, double defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Double.parseDouble(val);
    }

    public byte getByte(String name, byte defaultValue) {
        String val = this.getProperty(name);
        return (val == null || val.isEmpty()) ? defaultValue : Byte.parseByte(val);
    }

    public Configuration(){
        FileInputStream in = null;
        try {
            in = new FileInputStream("/config/config.properties");
            this.load(in);
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
