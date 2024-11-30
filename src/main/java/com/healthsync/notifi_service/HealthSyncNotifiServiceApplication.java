package com.healthsync.notifi_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HealthSyncNotifiServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthSyncNotifiServiceApplication.class, args);
	}

}
