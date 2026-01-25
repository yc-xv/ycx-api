package com.ycx.gateway;

import com.ycx.interfaces.InnerInterfaceInfoService;
import com.ycx.model.entity.InterfaceInfo;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

@EnableDubbo
@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class,
		DataSourceTransactionManagerAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class})
public class GatewayApplication {
	@DubboReference
	private InnerInterfaceInfoService innerInterfaceInfoService;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(GatewayApplication.class, args);
		GatewayApplication application = context.getBean(GatewayApplication.class);
		InterfaceInfo result = application.getInterfaceInfo("/api/name/user","POST");
		System.out.println(result);
	}

	public InterfaceInfo getInterfaceInfo(String url, String method) {
		return innerInterfaceInfoService.getInterfaceInfo(url, method);
	}
}
