package com.baihe.redis.test;

import redis.clients.jedis.Jedis;

public class RedisTest1
{
	 // redis����������
    static String host = "127.0.0.1";
    // �˿ں�
    static int port = 6379;
    
    public static void main(String[] args) {
       // ����redis�����Ͷ˿ں�ʵ����Jedis����
       Jedis jedis = new Jedis(host, port);
       // ���key-value�������key������ھ͸��Ǹö���
       jedis.set("name", "xmong");
       // ��ȡkey��valueֵ�����key�����ڷ���null
       String value1 = jedis.get("name");
       String value2 = jedis.get("test");
       System.out.println(value1+"-----"+value2);
       // ɾ��key-value�������key����������Դ˲���
       //jedis.del("name");
       // �ж�key�Ƿ���ڣ������ڷ���false���ڷ���true
       //jedis.exists("name");
    }
}
