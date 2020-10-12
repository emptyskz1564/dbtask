package com.liujiayi.clasip;

import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class ClasipApplication {
//
//    public static void main(String[] args) {
//        SpringApplication.run(ClasipApplication.class, args);
//    }
//
//}

@SpringBootApplication
public class ClasipApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ClasipApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ClasipApplication.class, args);
    }
}