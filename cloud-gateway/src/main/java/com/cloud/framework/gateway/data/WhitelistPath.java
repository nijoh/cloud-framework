package com.cloud.framework.gateway.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * 白名单数据
 * @author nijo_h
 * * @date 2023/11/10 22:25
 */
@Configuration
@ConfigurationProperties(prefix = "ignore")
public class WhitelistPath {
    private List<String> whitelist;

    public WhitelistPath() {
    }


    public WhitelistPath(List<String> whitelist) {
        this.whitelist = whitelist;
    }

    public List<String> getWhitelist() {
        return whitelist;
    }

    public void setWhitelist(List<String> whitelist) {
        this.whitelist = whitelist;
    }
}
