package com.baihe.redis.test;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisCollectionDealTest {
	
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

		//添加元素 :   jedis.sadd(集合名称, 对象);
        jedis.sadd("myLove", "汤汤");
        jedis.sadd("myLove", "慕贞");
        jedis.sadd("myLove", "贞贞");
        jedis.sadd("myLove", "汤汤");

        jedis.sadd("together", "汤汤");
        jedis.sadd("together", "昭昭");
			
		//删除元素 ： jedis.srem(集合名词, 对象);
		jedis.srem("myLove", "某某某");

		//集合元素查看 ：  jedis.smembers(集合名词);
	    Set myLoveSet = jedis.smembers("myLove");
	    Iterator iterator = myLoveSet.iterator();
	    while (iterator.hasNext()) {
	            System.out.println(iterator.next());
	     }

		 //查看两个集合并集 ：  jedis.sunion(集合名称1，集合名称2);
		 System.out.println("---------并集-----------");
        Set unionSet = jedis.sunion("myLove", "together");
        Iterator unionIterator = unionSet.iterator();
        while (unionIterator.hasNext()) {
            System.out.println(unionIterator.next());
        }

		//查看两个集合交集 ：  jedis.sinter(集合名称1，集合名称2);
        System.out.println("---------交集-----------");
        Set sinterSet = jedis.sinter("myLove", "together");
        Iterator sinterIterator = sinterSet.iterator();
        while (sinterIterator.hasNext()) {
            System.out.println(sinterIterator.next());
        }

		//查看两个集合差集 ：  jedis.smembers(集合名称1，集合名称2);
        System.out.println("-------差集-------------");
        Set sdiffSet = jedis.sdiff("myLove", "together");
        Iterator diffIterator = sdiffSet.iterator();
        while (diffIterator.hasNext()) {
            System.out.println(diffIterator.next());
        }
	}	
}
