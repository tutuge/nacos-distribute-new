package com.example.server;

import com.example.server.config.ChannelList;
import io.netty.channel.nio.NioEventLoopGroup;

import java.util.Random;
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
        sendMessage();
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

    public static void sendMessage() {
        System.out.println("开始消息发送");
        Thread thread = new Thread(() -> {
            for (; ; ) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                ChannelList instance = ChannelList.getInstance();
                instance.forEach((k, v) -> {
                    System.out.println("当前的channel名称：" + k);
                    Random random = new Random();
                    int i = random.nextInt(1000);
                    System.out.println("当前要发送的消息" + i);
                    System.out.println("当前channel" + v);
                    v.writeAndFlush(String.valueOf(i));
                });
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
