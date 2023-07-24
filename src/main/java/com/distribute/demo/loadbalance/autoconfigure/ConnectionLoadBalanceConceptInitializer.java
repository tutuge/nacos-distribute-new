package com.distribute.demo.loadbalance.autoconfigure;

import com.distribute.demo.loadbalance.core.server.ConnectionServer;
import com.distribute.demo.loadbalance.core.server.ConnectionServerManager;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author WangBin-00229693
 * @version 1.0
 * @date 2023/4/23 14:06
 */
@Component
@Slf4j
@AllArgsConstructor
public class ConnectionLoadBalanceConceptInitializer implements ApplicationRunner, Ordered {

    @Resource
    private ConnectionServerManager connectionServerManager;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("项目启动时候开始初始化运行");
        List<ConnectionServer> connectionServers = connectionServerManager.getConnectionServers();
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
