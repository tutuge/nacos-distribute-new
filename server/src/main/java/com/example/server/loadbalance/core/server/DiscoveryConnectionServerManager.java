//package com.distribute.demo.loadbalance.core.server;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.serviceregistry.Registration;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author WangBin-00229693
// * @version 1.0
// * @date 2023/4/23 13:26
// */
////@Slf4j
//public class DiscoveryConnectionServerManager implements com.distribute.demo.loadbalance.core.server.ConnectionServerManager {
//    private final DiscoveryClient discoveryClient;
//
//    private final Registration registration;
//
//    /**
//     * 本服务信息
//     */
//    private final com.distribute.demo.loadbalance.core.server.ConnectionServer local;
//
//    public DiscoveryConnectionServerManager(DiscoveryClient discoveryClient, Registration registration) {
//        this.discoveryClient = discoveryClient;
//        this.registration = registration;
//        this.local = new com.distribute.demo.loadbalance.core.server.ServiceInstanceConnectionServer(registration);
//    }
//
//    @Override
//    public void add(com.distribute.demo.loadbalance.core.server.ConnectionServer server) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void remove(com.distribute.demo.loadbalance.core.server.ConnectionServer server) {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public void clear() {
//        throw new UnsupportedOperationException();
//    }
//
//    @Override
//    public boolean isEqual(com.distribute.demo.loadbalance.core.server.ConnectionServer server1, com.distribute.demo.loadbalance.core.server.ConnectionServer server2) {
//        return server1.getHost().equals(server2.getHost()) && server1.getPort() == server2.getPort();
//    }
//
//    @Override
//    public com.distribute.demo.loadbalance.core.server.ConnectionServer getLocal() {
//        return local;
//    }
//
//    /**
//     * 获得所有除自身外的服务实例
//     *
//     * @return 所有除自身外的服务实例
//     */
//    @Override
//    public List<com.distribute.demo.loadbalance.core.server.ConnectionServer> getConnectionServers() {
//        List<com.distribute.demo.loadbalance.core.server.ConnectionServer> servers = new ArrayList<>();
//        List<ServiceInstance> instances = discoveryClient.getInstances(registration.getServiceId());
//        for (ServiceInstance instance : instances) {
//            com.distribute.demo.loadbalance.core.server.ConnectionServer server = newConnectionServer(instance);
//            //log.info("当前获得的实例为{}", instance);
//            //log.info("当前获得的server为{}", server);
//            if (isEqual(local, server)) {
//                continue;
//            }
//            servers.add(server);
//        }
//        return servers;
//    }
//
//    public com.distribute.demo.loadbalance.core.server.ConnectionServer newConnectionServer(ServiceInstance instance) {
//        return new com.distribute.demo.loadbalance.core.server.ServiceInstanceConnectionServer(instance);
//    }
//
//}
