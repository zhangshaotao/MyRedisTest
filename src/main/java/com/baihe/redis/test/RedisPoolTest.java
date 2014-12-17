package com.baihe.redis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPoolTest {
	
	public static JedisPool pool;
    // ��̬�����ʼ��������
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
       // ����redis�����ļ�
      // ResourceBundle bundle = ResourceBundle.getBundle("redis",Locale.getDefault());
       if (bundle == null)
       {
           throw new IllegalArgumentException("[redis.properties] is not found!");
       }
       // ����jedis������ʵ��
       JedisPoolConfig config = new JedisPoolConfig();
       
       config.setMaxActive(Integer.parseInt((String)bundle.get("jedis.pool.maxActive")));
       config.setMaxIdle(Integer.parseInt((String)bundle.get("jedis.pool.maxIdle")));
       config.setMaxWait(Long.parseLong((String)bundle.get("jedis.pool.maxWait")));
       config.setTestOnBorrow(Boolean.parseBoolean((String)bundle.get("jedis.pool.testOnBorrow")));
       config.setTestOnReturn(Boolean.parseBoolean((String)bundle.get("jedis.pool.testOnReturn")));
       // ��������ʵ����jedis��
       pool = new JedisPool(config, (String)bundle.get("redis.ip"), Integer.parseInt((String)bundle.get("redis.port")));
       
       // ���ó�������ֵ
       /*config.setMaxActive(Integer.valueOf(bundle.getString("redis.pool.maxActive")));
       config.setMaxIdle(Integer.valueOf(bundle.getString("redis.pool.maxIdle")));
       config.setMaxWait(Long.valueOf(bundle.getString("redis.pool.maxWait")));
       config.setTestOnBorrow(Boolean.valueOf(bundle.getString("redis.pool.testOnBorrow")));
       config.setTestOnReturn(Boolean.valueOf(bundle.getString("redis.pool.testOnReturn")));
       // ��������ʵ����jedis��
       pool = new JedisPool(config, bundle.getString("redis.ip"), Integer.valueOf(bundle.getString("redis.port")));
       */
    }
    
    
    public static void test1()
    {
        // ��jedis���л�ȡһ��jedisʵ��
        Jedis jedis = pool.getResource();
  
        // ��ȡjedisʵ������Զ�redis�������һϵ�еĲ���
        jedis.set("name", "xmong");
        System.out.println(jedis.get("name"));
        jedis.del("name");
        System.out.println(jedis.exists("name"));
  
        // �ͷŶ���أ�����ȡjedisʵ��ʹ�ú�Ҫ�����󻹻�ȥ
        pool.returnResource(jedis);
     }
  
     public static void main(String[] args)
     {
        test1();// ִ��test1����
     }
}
