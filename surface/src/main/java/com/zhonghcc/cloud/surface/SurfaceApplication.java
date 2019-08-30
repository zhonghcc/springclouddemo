package com.zhonghcc.cloud.surface;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@ComponentScan(basePackages = {"com.zhonghcc.cloud.surface","com.zhonghcc.cloud.common.rpc"})
//@ComponentScan
@EnableFeignClients(basePackages = "com.zhonghcc.cloud.backend")
public class SurfaceApplication {

//	@LoadBalanced
//	@Bean
//	public RestTemplate loadbalancedRestTemplate() {
//		return new RestTemplate();
//	}

	public static void main(String[] args) {
		SpringApplication.run(SurfaceApplication.class, args);
	}

}
