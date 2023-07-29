package com.example.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
public class CustomerApplication {

//    public static void main(String[] args) {
//        SpringApplication.run(CustomerApplication.class, args);
//    }

    public static void main(String[] args) {
        NettyClient nettyClient = new NettyClient("127.0.0.1",12345);
        nettyClient.connect();
    }
}
