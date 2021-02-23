package com.xiaosi.movieservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xiaosi"})
@EnableDiscoveryClient //nacoszhu注册
@EnableFeignClients  //因为是调用端，得加这个，不然不识别client那个包的配置
@MapperScan("com.xiaosi.movieservice.mapper")
@EnableSwagger2
public class MovieApplication {
    public static void main(String[] args) {
        SpringApplication.run(MovieApplication.class, args);
    }
}
