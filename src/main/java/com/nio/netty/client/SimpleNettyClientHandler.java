package com.nio.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

/**
 * 简单Netty客户端Handler
 *
 * @author Yue
 * @date 2020/9/3
 */
public class SimpleNettyClientHandler extends ChannelHandlerAdapter {

    private static final Logger logger = Logger
            .getLogger(SimpleNettyClientHandler.class.getName());

    private final ByteBuf firstMessage;

    public SimpleNettyClientHandler() {
        byte[] req = "QUERY_TIME_ORDER".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg)
            throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, StandardCharsets.UTF_8);
        System.out.println("当前时间是：" + body);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // 释放资源
        logger.warning("出现未知异常"
                + cause.getMessage());
        ctx.close();
    }

}
