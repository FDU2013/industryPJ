package cn.itcast.gateway;

import cn.itcast.feign.clients.AuthClient;
import cn.itcast.feign.pojo.AuthResponse;
import cn.itcast.feign.pojo.AuthResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.Objects;

// @Order(-1)
@Component
public class AuthorizeFilter implements GlobalFilter, Ordered {

    private String[] skipAuthUrls = {"/auth/login"};

    @Autowired
    private AuthClient authClient;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        HttpHeaders headers = request.getHeaders();
        String url = request.getURI().getPath();
        String token = headers.getFirst("auth");
        String ID = headers.getFirst("ID");

        //跳过不需要验证的路径
        if(null != skipAuthUrls&& Arrays.asList(skipAuthUrls).contains(url)){
            return chain.filter(exchange);
        }

        //是否携带token
        if(token == null || token.equals("")){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }

        //验证一下token是否有效
        AuthResponse resp = authClient.verify(token,ID);
        if(!Objects.equals(resp.getCode(), AuthResponseCode.SUCCESS)){
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
            return exchange.getResponse().setComplete();
        }

        //验证一下身份和访问的url是否匹配
        if(url.startsWith("/user")){
            if(resp.getAccount().getRole().equals("user"))chain.filter(exchange);
        }
        if(url.startsWith("/admin")){
            if(resp.getAccount().getRole().equals("admin"))chain.filter(exchange);
        }
        if(url.startsWith("/shop")){
            if(resp.getAccount().getRole().equals("admin"))chain.filter(exchange);
        }
        if(url.startsWith("/money")){
            if(resp.getAccount().getRole().equals("admin"))chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();

    }

    @Override
    public int getOrder() {
        return -1;
    }
}
