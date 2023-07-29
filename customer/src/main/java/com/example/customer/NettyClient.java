package com.example.customer;

import com.example.customer.config.GlobalNettyExceptionHandler;
import com.example.customer.config.TextMessageDecoder;
import com.example.customer.config.TextMessageEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;


public class NettyClient {
    private String mHost;

    private int mPort;

    private NettyClientHandler mClientHandler = new NettyClientHandler();
    private final TextMessageDecoder textMessageDecoder =new TextMessageDecoder();
    private final TextMessageEncoder textMessageEncoder =new TextMessageEncoder();

    private ChannelFuture mChannelFuture;

    public NettyClient(String host, int port) {
        this.mHost = host;
        this.mPort = port;
    }

    public void connect() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    // KeepAlive
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // Handler
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {

                            ch.pipeline().addLast(textMessageDecoder);
                            ch.pipeline().addLast(textMessageEncoder);
                            ch.pipeline().addLast(mClientHandler);
                            //全局异常处理器
                            ch.pipeline().addLast(new GlobalNettyExceptionHandler());
                        }
                    });
            mChannelFuture = b.connect(mHost, mPort).sync();
            if (mChannelFuture.isSuccess()) {
                System.out.println("Client,连接服务端成功");
            }
            mChannelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}