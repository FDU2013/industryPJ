package cn.itcast.gateway;

import cn.itcast.feign.clients.AuthClient;
import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.config.DefaultFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(clients = AuthClient.class,defaultConfiguration = DefaultFeignConfiguration.class)
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
