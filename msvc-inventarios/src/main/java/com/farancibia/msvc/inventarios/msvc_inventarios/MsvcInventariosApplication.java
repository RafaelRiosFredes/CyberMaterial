package com.farancibia.msvc.inventarios.msvc_inventarios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcInventariosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcInventariosApplication.class, args);
	}

}
