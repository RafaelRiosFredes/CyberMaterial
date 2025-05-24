package com.rrios.msvc.boletas.msvc_boletas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcBoletasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcBoletasApplication.class, args);
	}

}
