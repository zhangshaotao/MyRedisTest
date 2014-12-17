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

		//���Ԫ�� :   jedis.sadd(��������, ����);
        jedis.sadd("myLove", "����");
        jedis.sadd("myLove", "Ľ��");
        jedis.sadd("myLove", "����");
        jedis.sadd("myLove", "����");

        jedis.sadd("together", "����");
        jedis.sadd("together", "����");
			
		//ɾ��Ԫ�� �� jedis.srem(��������, ����);
		jedis.srem("myLove", "ĳĳĳ");

		//����Ԫ�ز鿴 ��  jedis.smembers(��������);
	    Set myLoveSet = jedis.smembers("myLove");
	    Iterator iterator = myLoveSet.iterator();
	    while (iterator.hasNext()) {
	            System.out.println(iterator.next());
	     }

		 //�鿴�������ϲ��� ��  jedis.sunion(��������1����������2);
		 System.out.println("---------����-----------");
        Set unionSet = jedis.sunion("myLove", "together");
        Iterator unionIterator = unionSet.iterator();
        while (unionIterator.hasNext()) {
            System.out.println(unionIterator.next());
        }

		//�鿴�������Ͻ��� ��  jedis.sinter(��������1����������2);
        System.out.println("---------����-----------");
        Set sinterSet = jedis.sinter("myLove", "together");
        Iterator sinterIterator = sinterSet.iterator();
        while (sinterIterator.hasNext()) {
            System.out.println(sinterIterator.next());
        }

		//�鿴�������ϲ ��  jedis.smembers(��������1����������2);
        System.out.println("-------�-------------");
        Set sdiffSet = jedis.sdiff("myLove", "together");
        Iterator diffIterator = sdiffSet.iterator();
        while (diffIterator.hasNext()) {
            System.out.println(diffIterator.next());
        }
	}	
}
