package com.FrontConnectGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactivefeign.spring.config.EnableReactiveFeignClients;


@SpringBootApplication
@EnableReactiveFeignClients
public class ConnectGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConnectGameApplication.class, args);
	}



}
