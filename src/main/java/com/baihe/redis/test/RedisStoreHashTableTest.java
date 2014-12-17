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
		jedis.hset("myhome", "wife", "����");
		jedis.hset("myhome", "husband", "����");
		jedis.hset("myhome", "test", "����");
		
		System.out.println(jedis.hget("myhome", "wife"));
		
		//Map myhome = jedis.hgetAll("myhome");
		
		System.out.println("���� home Ů����:" + jedis.hget("myhome", "wife"));

		//ɾ��hash table ��key : jedis.hdel(hash table name, key);
		 System.out.println("�˷�����:��myhome������ɾ��" );
		 jedis.hdel("myhome", "test");


		//����hash table ��key����: Set myhomeKeys = jedis.hkeys(hash table name);

		Set myhomeKeys = jedis.hkeys("myhome");
		Iterator it = myhomeKeys.iterator();

		while (it.hasNext()) {
		     System.out.println("��ɫ��" + it.next());
		}

		//��ȡhash table ��value���ϣ�List myhomeValues = jedis.hvals(hash table name);
		List myhomeValues = jedis.hvals("myhome");

		for (int i = 0; i < myhomeValues.size(); i++) {
		     System.out.println("��Ա��" + myhomeValues.get(i));
		}
	}
}
