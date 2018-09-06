package cn.anyoufang.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Map;


public class RedisUtil {

	private static Logger logger = Logger.getLogger(RedisUtil.class);


	private static  JedisPool jedisPool;


	public static Configuration getConfig(){
		try {
			return new PropertiesConfiguration("resouces.properties");
		} catch (ConfigurationException e) {
			throw new RRException("获取配置文件失败，", e);
		}
	}
	private RedisUtil() {
		if (jedisPool == null) {
			Configuration config=getConfig();
			String host = config.getString("redis.host");
			int port = Integer.parseInt(config.getString("redis.port"));
			int timeout = Integer.parseInt(config.getString("redis.timeout"));
			int database = Integer.parseInt(config.getString("redis.database"));
			String pass=config.getString("redis.pass");
			JedisPoolConfig poolConfig = new JedisPoolConfig();
			// 控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
			// 如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
			poolConfig.setMaxTotal(10000);
			// 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			poolConfig.setMaxIdle(2000);
			// 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
			poolConfig.setMaxWaitMillis(1000 * 100);
			poolConfig.setTestOnBorrow(true);
			jedisPool = new JedisPool(poolConfig,host,port,timeout, pass,database);
		}

	}
	/**
	 * 关闭redis线程池
	 */
	@SuppressWarnings("deprecation")
	private static void returnJedis(Jedis jedis) {
		if(jedis != null){
			jedis.close();
		}
	}




	/**
	 * 验证是否存在
	 */
	public static  boolean checkRedis(String key){
		Jedis jedis = null;
		boolean falgs = false;
		try {
			jedis = jedisPool.getResource();
			falgs = jedis.exists(key);
		} catch (Exception e) {
			logger.error("redis验证是否存在异常：", e);
		} finally {
			returnJedis(jedis);
		}
		return falgs;

	}


	/**
	 * 添加值
	 * @param key
	 * @param object
	 * @return
	 */
	public static  void setValue(String key, Object object){
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.set(key, object.toString()); 
		} catch (Exception e) {
			logger.error("redis添加值异常：", e);
		} finally {
			returnJedis(jedis);
		}
	}

	/**
	 * 封装对象
	 * @param key
	 * @param object
	 * @return
	 * @throws Exception
	 */
	public  static String setObject(String key ,Object object){
		Jedis jedis = null;
		String params ="";
		try{
			jedis = jedisPool.getResource();
			params = jedis.set(key.getBytes(), SerializeUtil.serialize(object));
		}catch (Exception e){
			returnJedis(jedis);
			logger.error("redis封装对象异常：" , e);
		}finally {
			returnJedis(jedis);
		}
		return params;
	}

	/**
	 * 解析封装对象
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public  static Object getObject(String key){
		Jedis jedis = null;
		Object object = null;
		try{
			jedis = jedisPool.getResource();
			byte[] value = jedis.get(key.getBytes());
			object = SerializeUtil.unserialize(value);
		}catch (Exception e){
			returnJedis(jedis);
			logger.error("redis解析封装对象", e);

		}finally {
			returnJedis(jedis);
		}
		return object;
	}

	/**
	 * redis获取字节象
	 * @param key
	 * @return
	 */
	public  static byte[] getObjects(String key){
		Jedis jedis = null;
		byte[] value = null;
		try{
			jedis = jedisPool.getResource();
			value = jedis.get(key.getBytes());

		}catch (Exception e){
			returnJedis(jedis);
			logger.error("redis获取字节象", e);
		}finally {
			returnJedis(jedis);
		}
		return value;
	}



	/**
	 * 获取值
	 */
	public static String getValues(String key){
		Jedis jedis = null;
		String value = null;
		try{
			jedis = jedisPool.getResource();
			value  = jedis.get(key);
		}catch (Exception e){
			returnJedis(jedis);
			logger.error("redis获取值异常：", e);
		}finally {
			returnJedis(jedis);
		}

		return value;
	}

	/**
	 * 删除reids key
	 */
	public static void getrRemove(String key){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.del(key);

		}catch (Exception e){
			returnJedis(jedis);
			logger.error("删除reids key：", e);
		}finally {
			returnJedis(jedis);
		}

	}

	/**
	 * 存入Hash值
	 */
	public synchronized static void setHashKey(String key, Map<String,String> map){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.hmset(key, map);
		}catch (Exception e){
			logger.error("redis存入Hash值异常：", e);
		}finally {
			returnJedis( jedis);
		}

	}


	/**
	 * 获取Hash全部的值
	 * @throws Exception
	 */

	public  static Map<String, String> hgetAll(String key){
		Jedis jedis = null;
		Map<String, String> resultMap = null;
		try{
			jedis = jedisPool.getResource();
			resultMap =  jedis.hgetAll(key);
		}catch (Exception e){
			logger.error("获取Hash全部的值异常", e);
		}finally {
			returnJedis(jedis);
		}
		return resultMap;
	}


	/**
	 * 获取hash 里面的某个值
	 * @param key
	 * @param name
	 * @return
	 */
	public static String getRedisHght(String key, String name){
		Jedis jedis = null;
		String data = null;
		try{
			jedis = jedisPool.getResource();
			data = jedis.hget(key, name);
		}catch (Exception e){
			logger.error("获取Hash全部的值异常", e);
		}finally {
			returnJedis(jedis);
		}
		return data;

	}

	/**
	 * 修改hash 里面的字段值
	 * @param key
	 * @param map
	 */
	public static void updateHmset(String key, Map<String, String> map){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			jedis.hmset(key, map);
		}catch (Exception e){
			logger.error("修改hash 里面的字段值异常", e);
		}finally {
			returnJedis(jedis);
		}
	}


	/**
	 * 设置key过期时间 秒
	 * @param key
	 * @param time
	 */
	public static void setExpire(String key, Integer time){
		Jedis jedis = null;
		try{
			jedis = jedisPool.getResource();
			long statuc = jedis.expire(key, time);
			if(statuc != 1){
				jedis.expire(key, time);
			}
		}catch (Exception e){
			logger.error("获取Hash全部的值异常", e);
		}finally {
			returnJedis(jedis);
		}
	}
	
	/**
	 * 获取key 的时效
	 *
	 */
	public static Long getVulesTime(String key){
		Jedis jedis = null;
		long timeL = 0L;
		try{
			jedis = jedisPool.getResource();
			timeL = jedis.ttl(key); 
		}catch (Exception e){
			logger.error("获取Hash全部的值异常", e);
		}finally {
			returnJedis(jedis);
		}
		return timeL;
	}



	public static void main(String[] args)throws Exception{
		setValue("xxxx", "cccc");
		

	}

}
