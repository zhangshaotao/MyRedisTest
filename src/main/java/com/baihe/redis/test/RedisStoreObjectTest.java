package com.baihe.redis.test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.baihe.redis.bean.Person;
import com.baihe.redis.utils.SerializeUtil;

public class RedisStoreObjectTest {

	public static JedisPool pool;
	public static Jedis jedis;
	
	static
	{
		pool = RedisPoolTest.pool;
		jedis = pool.getResource();
	}
	
	public static void main(String[] args) {
		//set
		jedis.set("person:100".getBytes(), SerializeUtil.serialize(new Person(100, "zhangsan")));
        jedis.set("person:101".getBytes(), SerializeUtil.serialize(new Person(101, "bruce")));
		
        
        //get
		byte[] data100= jedis.get(("person:100").getBytes());
        Person person100 = (Person) SerializeUtil.unserialize(data100);
        System.out.println(String.format("person:100->id=%s,name=%s", person100.getId(), person100.getName()));
        
        byte[] data101= jedis.get(("person:101").getBytes());
        Person person101 = (Person) SerializeUtil.unserialize(data101);
        System.out.println(String.format("person:101->id=%s,name=%s", person101.getId(), person101.getName()));
        
        
	}
}
