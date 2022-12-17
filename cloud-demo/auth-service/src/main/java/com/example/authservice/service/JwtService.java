package com.example.authservice.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.example.authservice.pojo.Account;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    // 生产环境不能这么用
    private static final String KEY = "changeIt";
    private static final String ISSUER = "yao";


    private static final long TOKEN_EXP_TIME = 6000000;
    private static final String USER_NAME = "username";

    /**
     * 生成Token
     *
     * @param acct
     * @return
     */
    public String token(Account acct) {
        Date now = new Date();
        Algorithm algorithm = Algorithm.HMAC256(KEY);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withIssuedAt(now)
                .withExpiresAt(new Date(now.getTime() + TOKEN_EXP_TIME))
                .withClaim(USER_NAME, acct.getUsername())
//                .withClaim("ROLE", "")
                .sign(algorithm);

        //System.out.println("jwt generated user={}", acct.getUsername());
        return token;
    }

    /**
     * 校验Token
     *
     * @param token
     * @param username
     * @return
     */
    public boolean verify(String token, String username) {
        //log.info("verifying jwt - username={}", username);

        try {
            Algorithm algorithm = Algorithm.HMAC256(KEY);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .withClaim(USER_NAME, username)
                    .build();

            verifier.verify(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}
