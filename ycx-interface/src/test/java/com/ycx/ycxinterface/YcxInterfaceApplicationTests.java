package com.ycx.ycxinterface;

import com.ycx.ycxapisdk.client.YcxApiClient;
import com.ycx.ycxapisdk.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class YcxInterfaceApplicationTests {
	@Resource
	private YcxApiClient testApiClient;
	@Test
	void contextLoads() {
		String result = testApiClient.getNameByGet("YcxInterfaceApplicationTests");
		System.out.println(result);
		User user = new User();
		user.setUsername("dsdfdsf");
		String getUserNameByPost = testApiClient.getUsernameByPost(user);
		System.out.println(getUserNameByPost);
	}

}
