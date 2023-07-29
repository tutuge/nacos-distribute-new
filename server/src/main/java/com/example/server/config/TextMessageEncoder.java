package com.example.server.config;


import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * websocket 协议 json消息体编码
 */
@ChannelHandler.Sharable
public class TextMessageEncoder extends MessageToMessageEncoder<CharSequence> {

    private final Charset charset;

    public TextMessageEncoder() {
        this(Charset.defaultCharset());
    }

    public TextMessageEncoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        System.out.println("当前write字符为" + msg);
        super.write(ctx, msg, promise);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {
        if (msg.length() == 0) {
            return;
        }
        System.out.println("当前字符为" + msg);

        out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg),
                this.charset));

    }
}
