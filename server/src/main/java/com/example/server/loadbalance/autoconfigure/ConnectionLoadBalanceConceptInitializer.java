//package com.distribute.demo.loadbalance.autoconfigure;
//
//import com.distribute.demo.loadbalance.core.server.ConnectionServerManager;
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.boot.ApplicationArguments;
//import org.springframework.boot.ApplicationRunner;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.core.Ordered;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * @author WangBin-00229693
// * @version 1.0
// * @date 2023/4/23 14:06
// */
//@Component
////@Slf4j
//@AllArgsConstructor
//public class ConnectionLoadBalanceConceptInitializer implements ApplicationRunner, Ordered {
//
//    private final ConnectionServerManager connectionServerManager;
//    private final DiscoveryClient discoveryClient;
//
//    @Override
//    public void run(ApplicationArguments args) throws Exception {
//
//        //log.info("项目启动时候开始初始化运行");
////        List<ConnectionServer> connectionServers = connectionServerManager.getConnectionServers();
//        List<String> services = discoveryClient.getServices();
//        for (String name : services) {
//            //log.info("当前服务的名称：{}", name);
//        }
////        for (ConnectionServer server : connectionServers) {
////            //log.info("当前服务的名称：{} host:{} port:{}", server.getServiceId(), server.getHost(), server.getPort());
////        }
//    }
//
//    @Override
//    public int getOrder() {
//        return 0;
//    }
//}
