package com.violetfreesia.carnation.test.config;

import com.violetfreesia.carnation.autoconfigure.CustomizeAuthClass;
import com.violetfreesia.carnation.handler.AuthSuccessHandler;
import com.violetfreesia.carnation.support.ExcludeUrlPatterns;
import com.violetfreesia.carnation.test.sercurity.AuthUserInfo;
import com.violetfreesia.carnation.test.sercurity.CheckPermission;
import com.violetfreesia.carnation.test.sercurity.CheckRole;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author violetfreesia
 * @date 2021-06-19
 */
@Configuration
public class CarnationConfig {

    @Bean
    public ExcludeUrlPatterns excludeUrlPatterns() {
        return ExcludeUrlPatterns.of("/login", "/public/**");
    }

    @Bean
    public AuthSuccessHandler authSuccessHandler() {
        return (request, response, userInfo) -> System.out.println(userInfo);
    }

    @Bean
    public CustomizeAuthClass customizeAuthClass() {
        return new CustomizeAuthClass(CheckRole.class,
                CheckPermission.class, AuthUserInfo.class);
    }
}
