package com.home_projects.imarket.services.security.endpoint;

import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.provider.endpoint.FrameworkEndpoint;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@FrameworkEndpoint
public class SecurityEndpoint {
    @Resource(name = "tokenServices")
    private ConsumerTokenServices tokenServices;

    @DeleteMapping(value = "/oauth/token")
    public void revokeToken(HttpServletRequest request) {
        String BEARER = "Bearer";
        String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorization != null && authorization.contains(BEARER)) {
            String token = authorization.substring(BEARER.length() + 1);
            tokenServices.revokeToken(token);
        }
    }
}
