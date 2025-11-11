package com.ems.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.client.RestTemplate;

@EnableFeignClients(basePackages = "com.ems.Client")
@EnableEurekaClient
@EntityScan(basePackages = "com.ems.model")
@EnableJpaRepositories(basePackages = "com.ems.repository")
@SpringBootApplication(scanBasePackages = "com.ems")
public class EventManagementApplication {

		private static final Logger logger = LoggerFactory.getLogger(EventManagementApplication.class);

		public static void main(String[] args) {
			SpringApplication.run(EventManagementApplication.class, args);


			logger.debug("Debug Message....");
			logger.info("Info Message....");
			logger.warn("Warn Message....");
			logger.error("Error Message....");


		}

	@Bean
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
	
}
