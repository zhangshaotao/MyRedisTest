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

		//����Ԫ�� ��jedis.zadd(��������˳��ֵ);
		 jedis.zadd("hackers", 1, "1");
		 jedis.zadd("hackers", 3, "2");
		 jedis.zadd("hackers", 2, "3");
		 jedis.zadd("hackers", 2, "4");

		//note:�������˳����������˳���һ���͸��ݲ����Ⱥ�������

		//ɾ��Ԫ�� �� jedis.zrem(������, "Ԫ��ֵ");
		jedis.zrem("hackers", "4");

		//Ԫ������jedis.zcard("������");
		jedis.zcard("hackers");

		//���ؼ���Ԫ�� �� jedis.zrange("������",startԪ��,stopԪ��);

		//note��
		//��0��ʾ���򼯵�һ����Ա����1��ʾ���򼯵ڶ�����Ա���Դ����ơ�
		//��Ҳ����ʹ�ø����±꣬��-1��ʾ���һ����Ա��

		Set stringSet = jedis.zrange("hackers", 0, -1);//��һ�������һ��
		Iterator it2 = stringSet.iterator();

		while (it2.hasNext()) {
		      System.out.println(it2.next());
		}


	}
}
