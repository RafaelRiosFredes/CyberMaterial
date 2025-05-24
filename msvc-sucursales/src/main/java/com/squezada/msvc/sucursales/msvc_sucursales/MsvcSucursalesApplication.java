package com.squezada.msvc.sucursales.msvc_sucursales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcSucursalesApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcSucursalesApplication.class, args);
	}

}
