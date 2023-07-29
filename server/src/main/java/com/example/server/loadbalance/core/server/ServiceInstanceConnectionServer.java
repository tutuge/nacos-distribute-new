//package com.distribute.demo.loadbalance.core.server;
//
//import lombok.AllArgsConstructor;
//import org.springframework.cloud.client.ServiceInstance;
//
//import java.net.URI;
//import java.util.Map;
//
///**
// * @author WangBin-00229693
// * @version 1.0
// * @date 2023/4/23 13:25
// */
//@AllArgsConstructor
//public class ServiceInstanceConnectionServer implements com.distribute.demo.loadbalance.core.server.ConnectionServer {
//
//    private ServiceInstance instance;
//
//    @Override
//    public String getInstanceId() {
//        return instance.getInstanceId();
//    }
//
//    @Override
//    public String getServiceId() {
//        return instance.getServiceId();
//    }
//
//    @Override
//    public String getHost() {
//        return instance.getHost();
//    }
//
//    @Override
//    public int getPort() {
//        return instance.getPort();
//    }
//
//    @Override
//    public Map<String, String> getMetadata() {
//        return instance.getMetadata();
//    }
//
//    @Override
//    public URI getUri() {
//        return instance.getUri();
//    }
//
//    @Override
//    public String getScheme() {
//        return instance.getScheme();
//    }
//
//    @Override
//    public boolean isSecure() {
//        return instance.isSecure();
//    }
//}
