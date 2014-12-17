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
		
		jedis.rpop("iphone"); //从list的头部删除元素

		//从list的尾部删除元素  : jedis.lpop("home"); 
		jedis.lpop("iphone"); //从list的尾部删除元素

		//排序 :   List listSort = jedis.sort(list name);
		List listSort = jedis.sort("iphone");
	}
}
