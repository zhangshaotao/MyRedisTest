package com.baihe.redis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolTest {
	
	public static JedisPool pool;
    // 静态代码初始化池配置
    static
    {
       InputStream in = ClassLoader.getSystemResourceAsStream("redis.properties");
       
       Properties bundle = new Properties();
       try 
       {
    	   bundle.load(in);
    	   
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       // 加载redis配置文件
      // ResourceBundle bundle = ResourceBundle.getBundle("redis",Locale.getDefault());
       if (bundle == null)
       {
           throw new IllegalArgumentException("[redis.properties] is not found!");
       }
       // 创建jedis池配置实例
       JedisPoolConfig config = new JedisPoolConfig();
       
       config.setMaxActive(Integer.parseInt((String)bundle.get("jedis.pool.maxActive")));
       config.setMaxIdle(Integer.parseInt((String)bundle.get("jedis.pool.maxIdle")));
       config.setMaxWait(Long.parseLong((String)bundle.get("jedis.pool.maxWait")));
       config.setTestOnBorrow(Boolean.parseBoolean((String)bundle.get("jedis.pool.testOnBorrow")));
       config.setTestOnReturn(Boolean.parseBoolean((String)bundle.get("jedis.pool.testOnReturn")));
       // 根据配置实例化jedis池
       pool = new JedisPool(config, (String)bundle.get("redis.ip"), Integer.parseInt((String)bundle.get("redis.port")));
       
       // 设置池配置项值
       /*config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
       config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
       config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
       config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
       config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
       // 根据配置实例化jedis池
       pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
       */
    }
    
    
    public static void test1()
    {
        // 从jedis池中获取一个jedis实例
        Jedis jedis = pool.getResource();
  
        // 获取jedis实例后可以对redis服务进行一系列的操作
        jedis.set("name", "xmong");
        System.out.println(jedis.get("name"));
        jedis.del("name");
        System.out.println(jedis.exists("name"));
  
        // 释放对象池，即获取jedis实例使用后要将对象还回去
        pool.returnResource(jedis);
     }
  
     public static void main(String[] args)
     {
        test1();// 执行test1方法
     }
}
