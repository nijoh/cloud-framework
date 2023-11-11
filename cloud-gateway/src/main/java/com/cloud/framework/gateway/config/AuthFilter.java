package com.cloud.framework.gateway.config;

import com.alibaba.fastjson.JSON;
import com.cloud.framework.gateway.data.WhitelistPath;
import com.cloud.framework.integrate.auth.TokenUtil;
import com.cloud.framework.model.common.constant.CloudConstant;
import com.cloud.framework.model.common.enums.HttpEnum;
import com.cloud.framework.model.common.result.BaseResult;
import com.cloud.framework.utils.DefaultUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.MalformedJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @author nijo_h * @date 2023/11/10 22:24
 */
@Component
public class AuthFilter implements GlobalFilter, Ordered {
    @Autowired
    private WhitelistPath whitelistPath;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String requestPath = exchange.getRequest().getPath().value();
        // 过滤白名单
        if (validateWhiteList(requestPath)) {
            // 执行一个责任链
            return chain.filter(exchange);
        }
        // 获取token
        List<String> requestHeaderList = DefaultUtil
            .defaultEditArrayList(exchange.getRequest().getHeaders().get(CloudConstant.REQUEST_AUTHORIZATION_TOKEN));
        try {
            String authorizationToken = requestHeaderList.stream().findFirst().orElse("");
            Claims claims = TokenUtil.parseJWT(authorizationToken);
            String userInfoData = claims.getSubject();
        } catch (MalformedJwtException exception) {
            return this.buildResponse(exchange.getResponse(),
                new BaseResult<>().fail(HttpEnum.UNAUTHORIZED.getCode(), HttpEnum.UNAUTHORIZED.getDesc()));
        }
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private boolean validateWhiteList(String requestPath) {
        List<String> whitelist = whitelistPath.getWhitelist();
        return whitelist.stream().anyMatch(path -> path.equals(requestPath));
    }

    private Mono<Void> buildResponse(ServerHttpResponse response, Object resultContent) {
        byte[] data = JSON.toJSONBytes(resultContent);
        DataBuffer buffer = response.bufferFactory().wrap(data);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        return response.writeWith(Mono.just(buffer));
    }
}
