package com.baihe.redis.test;

import java.util.Iterator;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisStoreListTest {
	public static JedisPool pool;
	public static Jedis jedis;
	
	static
	{
		pool = RedisPoolTest.pool;
		jedis = pool.getResource();
	}
	
	public static void main(String[] args)
	{
		jedis.del("iphone");
		for(int i=0; i<100; i++)
		{
			jedis.rpush("iphone","iphone"+i );
		}
		
		List<String> iphoneList = jedis.lrange("iphone", 20, 40);
		
		Iterator<String> iphoneListIterator = iphoneList.iterator();
		while(iphoneListIterator.hasNext())
		{
			System.out.println(iphoneListIterator.next());
		}
		
		jedis.rpop("iphone"); //��list��ͷ��ɾ��Ԫ��

		//��list��β��ɾ��Ԫ��  : jedis.lpop("home"); 
		jedis.lpop("iphone"); //��list��β��ɾ��Ԫ��

		//���� :   List listSort = jedis.sort(list name);
		List listSort = jedis.sort("iphone");
	}
}
