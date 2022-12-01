package com.green.nowon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@SpringBootApplication
public class SpringWebGoods01Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringWebGoods01Application.class, args);
	}

	//form hidden type 으로 name="_method" 전달시 처리해주는 필터적용하려고
	// @DeleteMapping, @PutMapping, @PatchMapping
	@Bean
	HiddenHttpMethodFilter hiddenHttpMethodFilter(){
		return new HiddenHttpMethodFilter();
	}
}
