package com.liyk.AOPAuth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
// 开启切面
@EnableAspectJAutoProxy
// 开启事务回滚
@EnableTransactionManagement
// 开启@WebListener
@ServletComponentScan
public class AopAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopAuthApplication.class, args);
	}
}
