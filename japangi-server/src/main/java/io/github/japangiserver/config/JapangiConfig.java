package io.github.japangiserver.config;

import io.wwan13.dispatchersorvlet.configuration.EnableSocketServer;
import io.wwan13.dispatchersorvlet.configuration.SocketServerConfigurer;
import io.wwan13.dispatchersorvlet.configuration.support.SocketServerPropertiesRegistry;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSocketServer
public class JapangiConfig implements SocketServerConfigurer {

    @Override
    public void setup(SocketServerPropertiesRegistry registry) {
        registry
            .port(8070);
    }
}
