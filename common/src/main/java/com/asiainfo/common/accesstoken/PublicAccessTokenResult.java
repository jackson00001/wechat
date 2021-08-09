package com.asiainfo.common.accesstoken;

import lombok.Data;

@Data
public class PublicAccessTokenResult {

    private String accessToken;

    private long expiresIn;

    private String errcode;

    private String errmsg;

    public PublicAccessToken getPublicAccessToken() {
        return new PublicAccessToken(accessToken, expiresIn);
    }
}
