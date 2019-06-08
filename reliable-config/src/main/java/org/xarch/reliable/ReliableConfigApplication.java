package org.xarch.reliable;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ReliableConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliableConfigApplication.class, args);
	}

}
