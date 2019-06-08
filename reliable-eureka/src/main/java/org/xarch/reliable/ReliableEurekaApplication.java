package org.xarch.reliable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ReliableEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableEurekaApplication.class, args);
	}

}
