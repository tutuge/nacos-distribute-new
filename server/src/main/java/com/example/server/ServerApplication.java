package com.example.server;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.SpringApplication;

import java.util.concurrent.ThreadFactory;

//@Slf4j
//@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        run();
        //log.info("执行netty");
//        SpringApplication.run(ServerApplication.class, args);
    }

    public static void run() {
        ThreadFactory bossThreadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("nio-boss-" + thread.getId());
            return thread;
        };

        ThreadFactory workerThreadFactory = r -> {
            Thread thread = new Thread(r);
            thread.setName("nio-worker-" + thread.getId());
            return thread;
        };
        NettyServer nettyServer = new NettyServer(12345,
                new NioEventLoopGroup(bossThreadFactory),
                new NioEventLoopGroup(workerThreadFactory));
//        Thread thread = new Thread(() -> {
            nettyServer.bind();
//        });
        //log.info("设置netty线程为守护线程");
//        thread.setDaemon(true);
        //log.info("开始运行netty");
//        thread.start();
    }
}
