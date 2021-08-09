package com.asiainfo.common.accesstoken;

import lombok.Data;

@Data
public class PublicAccessToken {

    // token信息
    private String accessToken;

    // 失效时间秒
    private long expiresIn;

    public PublicAccessToken() {
    }

    public PublicAccessToken(String accessToken, long expiresTime) {
        this.accessToken = accessToken;
        this.expiresIn = expiresTime;
    }



}
