package com.distribute.demo.loadbalance.core.server;

import java.net.URI;
import java.util.Map;

/**
 * @author WangBin-00229693
 * @version 1.0
 * @date 2023/4/23 13:24
 */
public interface ConnectionServer {
    /**
     * 获得服务实例 id
     *
     * @return 服务实例 id
     */
    String getInstanceId();

    /**
     * 获得服务 id
     *
     * @return 服务 id
     */
    String getServiceId();

    /**
     * 获得 host
     *
     * @return host
     */
    String getHost();

    /**
     * 获得 port
     *
     * @return port
     */
    int getPort();

    /**
     * 获得元数据
     *
     * @return 元数据
     */
    Map<String, String> getMetadata();

    /**
     * 获得 {@link URI}
     *
     * @return {@link URI}
     */
    URI getUri();

    /**
     * 获得 Scheme
     *
     * @return Scheme
     */
    String getScheme();

    /**
     * 是否安全的
     *
     * @return 是否安全的
     */
    boolean isSecure();
}
