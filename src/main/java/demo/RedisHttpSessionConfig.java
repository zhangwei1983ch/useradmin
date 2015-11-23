package demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
@ConfigurationProperties(prefix = "redis")
public class RedisHttpSessionConfig {
	@Autowired
	private RedisSettings setting;

	private String host;
	private int port;

	@Bean
	public JedisConnectionFactory connectionFactory() throws Exception {
		JedisConnectionFactory factory = new JedisConnectionFactory();
		factory.setHostName(this.setting.getHost());
		factory.setPort(this.setting.getPort());
		factory.setUsePool(true);
		return factory;
	}
}
