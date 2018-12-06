package com.zemoso.kafkasample;

import org.apache.commons.pool.impl.GenericObjectPool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {GenericObjectPool.class})
public class KafkaSampleApplicationTests {

	@Test
	public void contextLoads() {
	}

}
