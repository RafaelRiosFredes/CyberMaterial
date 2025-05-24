package com.rrios.msvc.productos.msvc_productos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcProductosApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcProductosApplication.class, args);
	}

}
