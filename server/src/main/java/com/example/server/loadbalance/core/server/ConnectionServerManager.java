package com.example.server.loadbalance.core.server;

import java.util.List;

/**
 * @author WangBin-00229693
 * @version 1.0
 * @date 2023/4/23 13:23
 */
public interface ConnectionServerManager {
    void add(ConnectionServer server);

    void remove(ConnectionServer server);

    void clear();

    boolean isEqual(ConnectionServer server1, ConnectionServer server2);

    ConnectionServer getLocal();

    List<ConnectionServer> getConnectionServers();
}
