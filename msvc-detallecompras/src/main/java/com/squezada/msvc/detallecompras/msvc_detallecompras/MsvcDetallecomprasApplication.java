package com.squezada.msvc.detallecompras.msvc_detallecompras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class MsvcDetallecomprasApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsvcDetallecomprasApplication.class, args);
	}

}
