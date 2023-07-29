package com.example.server.config;

import io.netty.channel.Channel;

import java.util.concurrent.ConcurrentHashMap;

public class ChannelList extends ConcurrentHashMap<String, Channel> {

    private static volatile ChannelList LIST = null;

    private ChannelList() {
    }

    public static ChannelList getInstance() {
        if (LIST == null) {
            synchronized (ChannelList.class) {
                if (LIST == null) {
                    LIST = new ChannelList();
                }
            }
        }
        return LIST;
    }

    public void add(Channel channel) {
        System.out.println("添加channel" + channel.id().asShortText());
        this.putIfAbsent(channel.id().asShortText(), channel);
    }

    public void remove(Channel channel) {
        System.out.println("移除当前channel" + channel.id().asShortText());
        super.remove(channel.id().asShortText());
    }
}
