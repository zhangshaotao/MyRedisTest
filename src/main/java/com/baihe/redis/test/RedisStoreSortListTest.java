package com.baihe.redis.test;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisStoreSortListTest {

	public static JedisPool pool;
	public static Jedis jedis;
	
	static
	{
		pool = RedisPoolTest.pool;
		jedis = pool.getResource();
	}
	
	public static void main(String[] args)
	{
		Jedis jedis = new Jedis("127.0.0.1", 6379);

		//增加元素 ：jedis.zadd(集合名，顺序，值);
		 jedis.zadd("hackers", 1, "1");
		 jedis.zadd("hackers", 3, "2");
		 jedis.zadd("hackers", 2, "3");
		 jedis.zadd("hackers", 2, "4");

		//note:排序根据顺序号排序，如果顺序号一样就根据插入先后来排序

		//删除元素 ： jedis.zrem(集合名, "元素值");
		jedis.zrem("hackers", "4");

		//元素数：jedis.zcard("集合名");
		jedis.zcard("hackers");

		//返回集合元素 ： jedis.zrange("集合名",start元素,stop元素);

		//note：
		//以0表示有序集第一个成员，以1表示有序集第二个成员，以此类推。
		//你也可以使用负数下标，以-1表示最后一个成员，

		Set stringSet = jedis.zrange("hackers", 0, -1);//第一个到最后一个
		Iterator it2 = stringSet.iterator();

		while (it2.hasNext()) {
		      System.out.println(it2.next());
		}


	}
}
