package com.example.customer;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import jdk.nashorn.internal.runtime.linker.Bootstrap;

import java.nio.channels.SocketChannel;

public class NettyClient {
    private String mHost;

    private int mPort;

    private NettyClientHandler mClientHandler;

    private ChannelFuture mChannelFuture;

    public NettyClient(String host, int port) {
        this.mHost = host;
        this.mPort = port;
    }

    public void connect() {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            mClientHandler = new NettyClientHandler();
            b.group(workerGroup).channel(NioSocketChannel.class)
                    // KeepAlive
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // Handler
                    .handler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(mClientHandler);
                        }
                    });
            mChannelFuture = b.connect(mHost, mPort).sync();
            if (mChannelFuture.isSuccess()) {
                LogUtil.log("Client,连接服务端成功");
            }
            mChannelFuture.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}