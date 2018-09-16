package com.liyk.AOPAuth;

import com.liyk.AOPAuth.util.Encrypt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopAuthApplicationTests {

	//10470c3b4b1fed12c3baac014be15fac67c6e815
	@Test
	public void contextLoads() {
		String s = Encrypt.md5AndSha("123456");
		System.out.println(s);
	}

}
