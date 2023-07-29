package com.example.customer.config;


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
    public boolean acceptOutboundMessage(Object msg) throws Exception {
        return super.acceptOutboundMessage(msg);
    }

    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        super.write(ctx, msg, promise);
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, CharSequence msg, List<Object> out) throws Exception {
        if (msg.length() == 0) {
            return;
        }

        out.add(ByteBufUtil.encodeString(ctx.alloc(), CharBuffer.wrap(msg),
                this.charset));

    }
}
