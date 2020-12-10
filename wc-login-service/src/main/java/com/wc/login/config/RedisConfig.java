package com.wc.login.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

@Configuration
@EnableRedisHttpSession(maxInactiveIntervalInSeconds=3600*10, redisNamespace = "${spring.session.redis.namespace}")
@EnableRedisRepositories
public class RedisConfig {
	@Value("${spring.redis.host}")
	private String redisHost;
	@Value("${spring.redis.port}")
	private int redisPort;
	@Value("${spring.redis.password}")
	private String redisPassword;

	@Bean
	public LettuceConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(redisHost, redisPort);
		config.setPassword(redisPassword);
		LettuceConnectionFactory factory = new LettuceConnectionFactory(config);
		return factory; 
	}
	
	//쿠키를 사용하지 않고 xAuthToken을 통해 세션 인식
	@Bean
	public HttpSessionIdResolver httpSessionIdResolver() {
		return HeaderHttpSessionIdResolver.xAuthToken(); 
	}
	
	@Bean 
	public RedisTemplate<String, String> redisSessionTemplate() {
		RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
	    redisTemplate.setConnectionFactory(redisConnectionFactory());
	    redisTemplate.setKeySerializer(new StringRedisSerializer());
	    redisTemplate.setValueSerializer(new StringRedisSerializer());
		return redisTemplate;
	}
}
