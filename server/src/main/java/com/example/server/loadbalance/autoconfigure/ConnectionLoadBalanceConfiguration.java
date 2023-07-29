//package com.distribute.demo.loadbalance.autoconfigure;
//
//import com.distribute.demo.loadbalance.core.server.ConnectionServerManager;
//import com.distribute.demo.loadbalance.core.server.DiscoveryConnectionServerManager;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.client.discovery.DiscoveryClient;
//import org.springframework.cloud.client.serviceregistry.Registration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @author WangBin-00229693
// * @version 1.0
// * @date 2023/4/23 13:41
// */
//@Configuration(proxyBeanMethods = false)
//public class ConnectionLoadBalanceConfiguration {
//
//    @Bean
//    @ConditionalOnMissingBean
//    @ConditionalOnClass({DiscoveryClient.class, Registration.class})
//    public ConnectionServerManager connectionServerProvider(DiscoveryClient client,
//                                                            Registration registration) {
//        return new DiscoveryConnectionServerManager(client, registration);
//    }
//}
