package com.hygod.hygodclintsdk;

import com.hygod.hygodclintsdk.clint.HygodClint;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: hygod
 * @Date: 2023/2/25
 * @Time: 14:00
 * @Description:
 */
@Data
@Configuration
@ConfigurationProperties("hygod.clint")
@ComponentScan
public class HygodClintConfig {
    private String accessKey;
    private String secretKey;

    @Bean
    public HygodClint HygodClint(){
        return new HygodClint(accessKey, secretKey);
    }
}
