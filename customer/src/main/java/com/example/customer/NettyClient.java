package com.example.customer;

import com.example.customer.config.GlobalNettyExceptionHandler;
import com.example.customer.config.TextMessageDecoder;
import com.example.customer.config.TextMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


public class NettyClient {



    private String mHost;

    private int mPort;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();
    private final TextMessageDecoder textMessageDecoder = new TextMessageDecoder();
    private final TextMessageEncoder textMessageEncoder = new TextMessageEncoder();

    private ChannelFuture mChannelFuture;

    public NettyClient(String host, int port) {
        this.mHost = host;
        this.mPort = port;
    }

    public void start() {
        Bootstrap b = new Bootstrap();
        EventLoopGroup workerGroup = new NioEventLoopGroup(2);
        System.out.println("workerGroup" + workerGroup);
        System.out.println("b" + b);
        try {
            b.group(workerGroup).channel(NioSocketChannel.class)
                    // KeepAlive
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // Handler
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(textMessageDecoder);
                            ch.pipeline().addLast(textMessageEncoder);
                            ch.pipeline().addLast(new NettyClientHandler());
                            //全局异常处理器
                            ch.pipeline().addLast(new GlobalNettyExceptionHandler());
                        }
                    });
            mChannelFuture = b.connect(mHost, mPort).sync();
            if (mChannelFuture.isSuccess()) {
                System.out.println("Client,连接服务端成功");
            }
            mChannelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                public void operationComplete(ChannelFuture future) throws Exception {
                    //服务端离线，触发重连操作
                    reconnect();
                }
            }).sync();

        } catch (Exception e) {
            e.printStackTrace();
            reconnect();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }

    //重连服务端
    public void reconnect() {
        System.out.println("开始重连");
        executorService.submit(new Runnable() {
            public void run() {
                for (; ; ) {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                        start();
//                        mChannelFuture = b.connect(mHost, mPort).sync();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}