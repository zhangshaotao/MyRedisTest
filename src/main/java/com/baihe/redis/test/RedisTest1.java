package com.baihe.redis.test;

import redis.clients.jedis.Jedis;

public class RedisTest1
{
	 // redis服务器主机
    static String host = "127.0.0.1";
    // 端口号
    static int port = 6379;
    
    public static void main(String[] args) {
       // 根据redis主机和端口号实例化Jedis对象
       Jedis jedis = new Jedis(host, port);
       // 添加key-value对象，如果key对象存在就覆盖该对象
       jedis.set("name", "xmong");
       // 查取key的value值，如果key不存在返回null
       String value1 = jedis.get("name");
       String value2 = jedis.get("test");
       System.out.println(value1+"-----"+value2);
       // 删除key-value对象，如果key不存在则忽略此操作
       //jedis.del("name");
       // 判断key是否存在，不存在返回false存在返回true
       //jedis.exists("name");
    }
}
