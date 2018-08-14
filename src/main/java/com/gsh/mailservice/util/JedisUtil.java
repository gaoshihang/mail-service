package com.gsh.mailservice.util;

import com.gsh.mailservice.constant.RedisConstant;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class JedisUtil {
    private static String JEDIS_IP;
    private static int JEDIS_PORT;
    private static String JEDIS_PASSWORD;
    private static JedisPool jedisPool;

    static{
        JEDIS_IP = RedisConstant.REDIS_IP;
        JEDIS_PORT = RedisConstant.REDIS_PORT;
        JEDIS_PASSWORD = RedisConstant.REDIS_PASSWORD;
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(256);
        config.setMaxWaitMillis(5000L);
        config.setTestOnBorrow(true);
        config.setTestOnReturn(true);
        config.setTestWhileIdle(true);
        config.setMinEvictableIdleTimeMillis(60000L);
        config.setTimeBetweenEvictionRunsMillis(3000L);
        config.setNumTestsPerEvictionRun(-1);
        jedisPool = new JedisPool(config,JEDIS_IP,JEDIS_PORT,60000);
    }

    private static void close(Jedis jedis){
        try{
            jedisPool.returnResource(jedis);
        }catch (Exception e){
            if(jedis.isConnected()){
                jedis.quit();
                jedis.disconnect();
            }
        }
    }


    /**
     * 为某key做递增
     * @param key
     */
    public static void incr(String key){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.incr(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }

    /**
     * 是否在redis中存在
     * @param key
     * @return
     */
    public static boolean hasKey(String key){
        boolean flag = false;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            flag = jedis.exists(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
        return flag;
    }

    /**
     * 获取数据
     * @param key
     * @return
     */
    public static String get(String key){
        String value = null;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //返还到连接池
            close(jedis);
        }
        return value;
    }

    public static byte[] get(byte[] key){
        byte[] value = null;
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            value = jedis.get(key);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }

        return value;
    }

    public static void set(byte[] key, byte[] value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }

    public static void set(String key, String value){
        Jedis jedis = null;
        try{
            jedis = jedisPool.getResource();
            jedis.set(key,value);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            close(jedis);
        }
    }

    public static void set(String key, String value, int time) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static void set(byte[] key, byte[] value, int time) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.set(key, value);
            jedis.expire(key, time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 哈希表set
     * @param key
     * @param field
     * @param value
     */
    public static void hset(byte[] key, byte[] field, byte[] value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static void hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hset(key, field, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 哈希表获取数据
     *
     * @param key
     * @return
     */
    public static String hget(String key, String field) {

        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }

    public static byte[] hget(byte[] key, byte[] field) {

        byte[] value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            value = jedis.hget(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }

        return value;
    }

    /**
     * 哈希表删除数据
     * @param key
     * @param field
     */
    public static void hdel(byte[] key, byte[] field) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 存储REDIS队列 顺序存储
     * @param  key reids键名
     * @param  value 键值
     */
    public static void lpush(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static void lpush(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.lpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 存储REDIS队列 反向存储
     * @param  key reids键名
     * @param  value 键值
     */
    public static void rpush(byte[] key, byte[] value) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.rpush(key, value);
        } catch (Exception e) {
            e.printStackTrace();

        } finally {

            //返还到连接池
            close(jedis);

        }
    }

    /**
     * 将列表 source 中的最后一个元素(尾元素)弹出，并返回给客户端
     * @param  key reids键名
     * @param  destination 键值
     */
    public static void rpoplpush(byte[] key, byte[] destination) {

        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.rpoplpush(key, destination);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //返还到连接池
            close(jedis);

        }
    }

    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public static List lpopList(byte[] key) {

        List list = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            list = jedis.lrange(key, 0, -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return list;
    }

    /**
     * 获取队列数据
     * @param  key 键名
     * @return
     */
    public static byte[] rpop(byte[] key) {

        byte[] bytes = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            bytes = jedis.rpop(key);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            //返还到连接池
            close(jedis);

        }
        return bytes;
    }

    /**
     * 同时将多个域-值设置到哈希表key中
     * @param key
     * @param hash
     */
    public static void hmset(Object key, Map hash) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);

        }
    }

    public static void hmset(Object key, Map hash, int time) {
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            jedis.hmset(key.toString(), hash);
            jedis.expire(key.toString(), time);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);

        }
    }

    public static List hmget(Object key, String... fields) {
        List result = null;
        Jedis jedis = null;
        try {

            jedis = jedisPool.getResource();
            result = jedis.hmget(key.toString(), fields);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    /**
     * 返回哈希表中所有域
     * @param key
     * @return
     */
    public static Set hkeys(String key) {
        Set result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hkeys(key);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    public static List lrange(byte[] key, int from, int to) {
        List result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.lrange(key, from, to);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);

        }
        return result;
    }

    public static Map hgetAll(byte[] key) {
        Map result = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            result = jedis.hgetAll(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return result;
    }

    /**
     * 删除key
     * @param key
     */
    public static void del(String key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    /**
     * 删除key
     * @param key
     */
    public static void del(byte[] key) {

        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
    }

    public static long llen(byte[] key) {

        long len = 0;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            len = jedis.llen(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //返还到连接池
            close(jedis);
        }
        return len;
    }
}
