package com.wn.qian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wn.qian.modules.**.mapper") //扫描mapper的包，或者也可以在对应的mapper上加上@Mapper的注解
public class QianApplication {

    public static void main(String[] args) {
        SpringApplication.run(QianApplication.class, args);
    }

}
