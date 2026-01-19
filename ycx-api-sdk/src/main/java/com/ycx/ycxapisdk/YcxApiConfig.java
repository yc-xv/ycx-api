package com.ycx.ycxapisdk;

import com.ycx.ycxapisdk.client.YcxApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
// 这里给所有的配置加上前缀为"ycxapi.client"
@ConfigurationProperties("ycxapi.client")
@Data
@ComponentScan
public class YcxApiConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public YcxApiClient testApiClient() {return new YcxApiClient(accessKey, secretKey);}

}
