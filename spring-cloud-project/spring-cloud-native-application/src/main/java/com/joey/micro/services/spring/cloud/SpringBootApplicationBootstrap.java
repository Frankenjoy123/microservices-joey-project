package com.joey.micro.services.spring.cloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaowu.zhou@tongdun.cn on 2018/7/31.
 */
@EnableAutoConfiguration
@RestController
public class SpringBootApplicationBootstrap {


    public static void main(String[] args) {


        AnnotationConfigApplicationContext annotationContext
                = new AnnotationConfigApplicationContext();
        annotationContext.setId("Joey");

        //注册一个bean
        annotationContext.registerBean("helloWorld",
                String.class,"hello , world");

        annotationContext.registerBean("helloWorld2",
                String.class,"hello , world2");

        annotationContext.refresh();


        new SpringApplicationBuilder(SpringBootApplicationBootstrap.class)
                .parent(annotationContext)
                .run(args);

    }


    @Autowired
    @Qualifier("helloWorld")
    private String message;


    @RequestMapping("")
    public String index(){
        return message;
    }


}
