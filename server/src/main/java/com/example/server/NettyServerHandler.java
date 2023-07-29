package com.example.server;

import com.example.server.config.ChannelList;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.string.StringDecoder;

import java.util.List;

/**
 * @author wangbin
 */
//@Slf4j
public class NettyServerHandler extends StringDecoder {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server,channelActive");
        ChannelList.getInstance().add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Server,接收到客户端发来的消息:" + msg);
        System.out.println(msg.toString());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("Server,exceptionCaught");
        cause.printStackTrace();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Server,channelInactive");
        ChannelList.getInstance().remove(ctx.channel());
    }
}