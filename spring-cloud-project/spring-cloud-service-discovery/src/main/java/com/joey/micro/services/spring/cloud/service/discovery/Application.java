package com.joey.micro.services.spring.cloud.service.discovery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaowu.zhou@tongdun.cn on 2018/8/5.
 */
@SpringBootApplication
@RestController
public class Application {

    @RequestMapping("/")
    public String home() {
        return "Hello world";
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(Application.class).web(true).run(args);
    }


    @Autowired
    private DiscoveryClient discoveryClient;


    @GetMapping("/services")
    public List<String> getServices(){

        return discoveryClient.getServices();
    }


    @GetMapping("/service/instances/{serviceId}")
    public List<String> getServices(@PathVariable("serviceId") String serviceId){

        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);

        return instances.stream().map( i -> i.getServiceId() + "->" + i.getHost()+":"+i.getPort())
                .collect(Collectors.toList());

    }


}
