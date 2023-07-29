package com.example.server;

import com.example.server.config.TextMessageDecoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;


////@Slf4j
public class NettyServer {
    private int mPort;

    private final EventLoopGroup bossGroup;
    private final EventLoopGroup workerGroup;
    private final NettyServerHandler nettyServerHandler = new NettyServerHandler();
    private final TextMessageDecoder textMessageDecoder =new TextMessageDecoder();

    public NettyServer(int port, EventLoopGroup bossGroup, EventLoopGroup workerGroup) {
        this.mPort = port;
        this.bossGroup = bossGroup;
        this.workerGroup = workerGroup;
    }

    public void bind() {
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    // 指定连接队列大小
                    .option(ChannelOption.SO_BACKLOG, 128)
                    //KeepAlive
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //Handler
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            /**
                             *因为基于http协议，使用http的编码和解码器
                             */
//                            ch.pipeline().addLast(new HttpServerCodec());
                            /**
                             * 是以块方式写，添加ChunkedWriteHandler处理器
                             */
//                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            /**
                             *说明
                             *1. http数据在传输过程中是分段, HttpObjectAggregator ，就是可以将多个段聚合
                             * 2. 这就就是为什么，当浏览器发送大量数据时，就会发出多次http请求
                             */
//                            ch.pipeline().addLast(new HttpObjectAggregator(4 * 1024));
                            ch.pipeline().addLast(textMessageDecoder);
                            ch.pipeline().addLast(nettyServerHandler);


                        }
                    });
            ChannelFuture f = b.bind(mPort).sync();
            if (f.isSuccess()) {
                //log.info("Server,启动Netty服务端成功，端口号:" + mPort);
            }
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bossGroup != null && !bossGroup.isShuttingDown() && !bossGroup.isShutdown()) {
                try {
                    bossGroup.shutdownGracefully();
                } catch (Exception ignore) {
                }
            }

            if (workerGroup != null && !workerGroup.isShuttingDown() && !workerGroup.isShutdown()) {
                try {
                    workerGroup.shutdownGracefully();
                } catch (Exception ignore) {
                }
            }
        }
    }


}
