package com.wc.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;

@SpringBootApplication(scanBasePackages={"com.wc.login"}, exclude= {RedisRepositoriesAutoConfiguration.class})
public class WcLoginServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WcLoginServiceApplication.class, args);
	}

}
