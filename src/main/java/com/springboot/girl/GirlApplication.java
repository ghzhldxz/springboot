package com.springboot.girl;

import com.alibaba.fastjson.JSONObject;
import com.springboot.girl.bean.Account;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.lang.ref.SoftReference;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@SpringBootApplication
@ComponentScan("com.springboot.girl")
//@MapperScan("com.springboot.girl.mapper")
public class GirlApplication {

	public static void main(String[] args) {
		//int i = 1 / 0;
		//int j = 1 % 0;
		double k = 1.0 / 0;//double和float可以除以0,Infinity
		System.out.println(k);
		//Integer[] strings = new Integer[];

		/*List<?>[] lists = new ArrayList<?>[2];
		List<Integer>[] lsa = (List<Integer>[]) Array.newInstance(ArrayList.class, 4);
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<String> li = new ArrayList<String>();
		li.add("asdf");
		oa[1] = li;
		System.out.println("=========================="+lsa[1].get(0));*/
		//SpringApplication.run(GirlApplication.class, args);


		List<String>[] lsa = (List<String>[])Array.newInstance(ArrayList.class, 4);
		Object o = lsa;
		Object[] oa = (Object[]) o;
		List<Integer> li = new ArrayList<Integer>();
		li.add(new Integer(3));
// Correct.
		oa[1] = li;
// Run time error, but cast is explicit.
		System.out.println("=========================="+(lsa[1].get(0)));  //2


	}
}
