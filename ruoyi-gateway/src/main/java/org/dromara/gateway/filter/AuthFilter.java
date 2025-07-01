package org.dromara.gateway.filter;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.httpauth.basic.SaHttpBasicUtil;
import cn.dev33.satoken.reactor.context.SaReactorSyncHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import org.dromara.common.core.constant.HttpStatus;
import org.dromara.common.core.utils.SpringUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.gateway.config.properties.IgnoreWhiteProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.Base64;

/**
 * [Sa-Token 权限认证] 拦截器
 *
 * @author Lion Li
 */
@Configuration
public class AuthFilter {

    /**
     * 注册 Sa-Token 全局过滤器
     */
    @Bean
    public SaReactorFilter getSaReactorFilter(IgnoreWhiteProperties ignoreWhite) {
        return new SaReactorFilter()
            // 拦截地址
            .addInclude("/**")
            .addExclude("/favicon.ico", "/actuator", "/actuator/**", "/resource/sse")
            // 鉴权方法：每次访问进入
            .setAuth(obj -> {
                // 登录校验 -- 拦截所有路由
                SaRouter.match("/**")
                    .notMatch(ignoreWhite.getWhites())
                    .check(r -> {
                        ServerHttpRequest request = SaReactorSyncHolder.getExchange().getRequest();
                        if (isLikelyJwt(StpUtil.getTokenValue())) {
                            // JWT 方式鉴权
                            StpUtil.checkLogin();

                            // 进行 clientId 校验（你的原有逻辑）
                            String headerCid = request.getHeaders().getFirst(LoginHelper.CLIENT_KEY);
                            String paramCid = request.getQueryParams().getFirst(LoginHelper.CLIENT_KEY);
                            String clientId = StpUtil.getExtra(LoginHelper.CLIENT_KEY).toString();
                            if (!StringUtils.equalsAny(clientId, headerCid, paramCid)) {
                                throw NotLoginException.newInstance(StpUtil.getLoginType(),
                                    "-100", "客户端ID与Token不匹配", StpUtil.getTokenValue());
                            }
                        } else {
                            System.out.println("aa");
                        }
                    });
            }).setError(e -> {
                if (e instanceof NotLoginException) {
                    return SaResult.error(e.getMessage()).setCode(HttpStatus.UNAUTHORIZED);
                }
                return SaResult.error("认证失败，无法访问系统资源").setCode(HttpStatus.UNAUTHORIZED);
            });
    }

    /**
     * 判断是否是 JWT token（基于格式特征）
     *
     * @param token Bearer 后的字符串
     * @return true 表示是 JWT
     */
    public static boolean isLikelyJwt(String token) {
        if (token == null || token.trim().isEmpty()) {
            return false;
        }

        // JWT 一般为三段结构，用 "." 分隔，通常是 header.payload.signature
        String[] parts = token.split("\\.");
        if (parts.length != 3) {
            return false;
        }

        // 判断 header 是否是合法的 Base64 并包含 "alg"
        try {
            String headerJson = new String(Base64.getUrlDecoder().decode(parts[0]));
            return headerJson.contains("\"alg\"");
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * 判断是否是 API Key（辅助函数，根据格式特征识别）
     *
     * @param token Bearer 后的字符串
     * @return true 表示是自定义 API Key
     */
    public static boolean isLikelyApiKey(String token) {
        // 可扩展为以 sk-xxx 或 ak- 开头的自定义 key 格式
        return !isLikelyJwt(token);
    }

    /**
     * 对 actuator 健康检查接口 做账号密码鉴权
     */
    @Bean
    public SaReactorFilter actuatorFilter() {
        String username = SpringUtils.getProperty("spring.cloud.nacos.discovery.metadata.username");
        String password = SpringUtils.getProperty("spring.cloud.nacos.discovery.metadata.userpassword");
        return new SaReactorFilter()
            .addInclude("/actuator", "/actuator/**")
            .setAuth(obj -> {
                SaHttpBasicUtil.check(username + ":" + password);
            })
            .setError(e -> SaResult.error(e.getMessage()).setCode(HttpStatus.UNAUTHORIZED));
    }

}
