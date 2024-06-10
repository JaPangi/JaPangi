package io.github.japangiserver.config;

import io.wwan13.dispatchersorvlet.configuration.EnableSocketServer;
import io.wwan13.dispatchersorvlet.configuration.SocketServerConfigurer;
import io.wwan13.dispatchersorvlet.configuration.support.SocketServerPropertiesRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/** NOTE
 * Socket 연결 설정
 */
@Configuration
@EnableSocketServer
public class JapangiConfig implements SocketServerConfigurer {

    @Value(value = "${spring.socket.port}")
    private int socketPort;
    /** NOTE
     * Socket 포트 지정
     */
    @Override
    public void setup(SocketServerPropertiesRegistry registry) {
        registry
            .port(socketPort);
    }
}
