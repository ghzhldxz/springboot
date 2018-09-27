package com.springboot.girl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.bean.Account;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.springboot.girl")
//@MapperScan("com.springboot.girl.mapper")
public class GirlApplication {

	public static void main(String[] args) {
		SpringApplication.run(GirlApplication.class, args);
	}
}
