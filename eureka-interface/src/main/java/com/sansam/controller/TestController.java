package com.sansam.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/interface")
public class TestController {
	
	@RequestMapping("/test1")
	public String test1(){
		return "test ok!";
	}
}
