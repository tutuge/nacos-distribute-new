package com.example.server.loadbalance.core.server;


import lombok.Data;

import java.net.URI;
import java.util.Map;

/**
 * {@link com.distribute.demo.loadbalance.core.server.ConnectionServer} 的默认实现
 */
@Data
public class ConnectionServerImpl implements ConnectionServer {

    private String instanceId;

    private String serviceId;

    private String host;

    private int port;

    private Map<String, String> metadata;

    private URI uri;

    private String scheme;

    private boolean secure;
}
