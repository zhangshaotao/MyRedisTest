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
		//---exec ִ���������������-------------
		 Transaction t = jedis.multi();//��ʼ����
		 t.set("husband".getBytes(), "����".getBytes());
		 t.set("wife".getBytes(), "����".getBytes());
		 t.exec();//ִ������


		//------discard ȡ��ִ������������---------
		 Transaction t2 = jedis.multi();
		 t2.set("test1", "ture love");
		 t2.exec(); //�ύ����
		 t2 = jedis.multi();
		 t2.set("test1", "Ta");
		 t2.discard(); //  �ع�����

		 String husband = jedis.get("husband");
		 String wife = jedis.get("wife");
		 System.out.println("husband:" + husband);
		 System.out.println("wife:" + wife);
		 
		 System.out.println(jedis.get("test1"));
	}
}
