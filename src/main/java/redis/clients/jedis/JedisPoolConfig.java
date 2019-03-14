package redis.clients.jedis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class JedisPoolConfig extends GenericObjectPoolConfig {
  public JedisPoolConfig() {
    // defaults to make your life with connection pool easier :)

    // 是否开启空闲资源监测，默认false
    setTestWhileIdle(true);

    // 空闲资源的检测周期(单位为毫秒)
    setMinEvictableIdleTimeMillis(60000);

    // 资源池中资源最小空闲时间(单位为毫秒)，达到此值后空闲资源将被移除
    setTimeBetweenEvictionRunsMillis(30000);

    // 做空闲资源检测时，每次的采样数
    // -1 表示对所有连接做空闲监测
    setNumTestsPerEvictionRun(-1);
  }
}
