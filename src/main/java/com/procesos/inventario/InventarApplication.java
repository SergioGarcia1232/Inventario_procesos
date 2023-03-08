package com.procesos.inventario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class InventarApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventarApplication.class, args);
	}

}
