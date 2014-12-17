package com.baihe.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.Transaction;

public class RedisTransactionTest {
	
	public static JedisPool pool;
	public static Jedis jedis;
	
	static
	{
		pool = RedisPoolTest.pool;
		jedis = pool.getResource();
	}
	
	public static void main(String[] args)
	{
		//---exec 执行事务队列内命令-------------
		 Transaction t = jedis.multi();//开始事务
		 t.set("husband".getBytes(), "昭昭".getBytes());
		 t.set("wife".getBytes(), "汤汤".getBytes());
		 t.exec();//执行事务


		//------discard 取消执行事务内命令---------
		 Transaction t2 = jedis.multi();
		 t2.set("test1", "ture love");
		 t2.exec(); //提交事务
		 t2 = jedis.multi();
		 t2.set("test1", "Ta");
		 t2.discard(); //  回滚事务

		 String husband = jedis.get("husband");
		 String wife = jedis.get("wife");
		 System.out.println("husband:" + husband);
		 System.out.println("wife:" + wife);
		 
		 System.out.println(jedis.get("test1"));
	}
}
