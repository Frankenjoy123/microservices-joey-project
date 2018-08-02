package com.joey.micro.services.spring.cloud.server;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.config.environment.Environment;
import org.springframework.cloud.config.environment.PropertySource;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.config.server.environment.EnvironmentRepository;
import org.springframework.context.annotation.Bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiaowu.zhou@tongdun.cn on 2018/8/1.
 */
@SpringBootApplication
@EnableConfigServer
public class SpringCloudConfigServer {

    public static void main(String[] args) {

        new SpringApplicationBuilder(SpringCloudConfigServer.class)
                .run(args);

    }


    @Bean
    public EnvironmentRepository getEnvironmentRepository(){


        return new EnvironmentRepository() {
            @Override
            public Environment findOne(String application, String profile, String label) {

                Environment environment = new Environment("dev",profile);

                List<PropertySource> propertySourceList = environment.getPropertySources();

                Map<String ,Object> par = new HashMap<>();
                par.put("name","Jack");
                PropertySource propertySource = new PropertySource("xiaomage",par);

                propertySourceList.add(propertySource);

                return environment;
            }
        };
    }
}
