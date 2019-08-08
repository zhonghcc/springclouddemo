package com.zhonghcc.cloud.surface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan
public class SurfaceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SurfaceApplication.class, args);
	}

}
