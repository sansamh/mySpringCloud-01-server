package com.sansam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/client")
public class TestController {
	
	@Bean
	@LoadBalanced // 这个注解一定要加，不然LoadBalancerAutoConfiguration不会对它进行处理
	RestTemplate RestTemplate() {
		return new RestTemplate();
	}
	
	@Autowired
	private DiscoveryClient discoveryClient;
	
	@RequestMapping("/method1")
	public String method1(){
		List<ServiceInstance> instances = discoveryClient.getInstances("eureka-interface");
		ServiceInstance serviceInstance = instances.get(0);
		System.out.println("request url : "+"http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/interface/test1");
		String object = new RestTemplate().getForObject("http://"+serviceInstance.getHost()+":"+serviceInstance.getPort()+"/interface/test1", String.class);
		return object;
	}
}
