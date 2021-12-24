package com.kevin.common.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.Payload;
import com.kevin.auth.JWTHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author kevin
 * @date 2021-12-20 17:41
 */
@Service
public class UserService {
    /**
     * 签发时定义的claim名
     */
    private final static String CLAIM_USERNAME = "name";

    public DecodedJWT getJwt() {
        return JWTHolder.getJwt();
    }

    public String getUsername() {
        return Optional.ofNullable(getJwt()).map(o -> o.getClaim(CLAIM_USERNAME).asString()).orElse("未登录");
    }

    public String getAccount() {
        return Optional.ofNullable(getJwt()).map(Payload::getSubject).orElse("unknown");
    }
}
