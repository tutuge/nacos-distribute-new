package com.example.customer.config;

import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import lombok.extern.slf4j.Slf4j;

/**
 * @author nobody
 * @date 2022/6/10
 */
//@Slf4j
public class GlobalNettyExceptionHandler extends ChannelDuplexHandler {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        if (cause != null) {
            cause.printStackTrace();
            System.out.println("读数据异常: " + cause);
        }
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        ctx.write(msg, promise.addListener((ChannelFutureListener) channelFuture -> {
            if (!channelFuture.isSuccess()) {
                Throwable cause = channelFuture.cause();
                cause.printStackTrace();
                System.out.println("写数据异常：" + cause);
            }
        }));
    }
}
