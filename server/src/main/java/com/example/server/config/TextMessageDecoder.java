package com.example.server.config;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.List;

/**
 * websocket 协议 json消息体解码
 *
 * @author none
 */
//@Slf4j
@ChannelHandler.Sharable
public class TextMessageDecoder extends MessageToMessageDecoder<ByteBuf> {
    private final Charset charset;

    public TextMessageDecoder() {
        this(Charset.defaultCharset());
    }

    public TextMessageDecoder(Charset charset) {
        if (charset == null) {
            throw new NullPointerException("charset");
        }
        this.charset = charset;
    }

    protected void decode(ChannelHandlerContext ctx, ByteBuf msg,
                          List<Object> out) throws Exception {
        String string = msg.toString(this.charset);
        System.out.println("当前字符串为："+string);
        out.add(string);
    }

}
