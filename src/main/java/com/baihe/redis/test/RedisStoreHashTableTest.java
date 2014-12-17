package com.baihe.redis.test;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisStoreHashTableTest {

	public static JedisPool pool;
	public static Jedis jedis;
	
	static
	{
		pool = RedisPoolTest.pool;
		jedis = pool.getResource();
	}
	
	public static void main(String[] args)
	{
		jedis.hset("myhome", "wife", "汤汤");
		jedis.hset("myhome", "husband", "昭昭");
		jedis.hset("myhome", "test", "困难");
		
		System.out.println(jedis.hget("myhome", "wife"));
		
		//Map myhome = jedis.hgetAll("myhome");
		
		System.out.println("昭昭 home 女主人:" + jedis.hget("myhome", "wife"));

		//删除hash table 的key : jedis.hdel(hash table name, key);
		 System.out.println("克服困难:把myhome的困难删除" );
		 jedis.hdel("myhome", "test");


		//遍历hash table 的key集合: Set myhomeKeys = jedis.hkeys(hash table name);

		Set myhomeKeys = jedis.hkeys("myhome");
		Iterator it = myhomeKeys.iterator();

		while (it.hasNext()) {
		     System.out.println("角色：" + it.next());
		}

		//获取hash table 的value集合：List myhomeValues = jedis.hvals(hash table name);
		List myhomeValues = jedis.hvals("myhome");

		for (int i = 0; i < myhomeValues.size(); i++) {
		     System.out.println("成员：" + myhomeValues.get(i));
		}
	}
}
