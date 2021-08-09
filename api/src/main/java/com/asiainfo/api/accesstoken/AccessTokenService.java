package com.asiainfo.api.accesstoken;

import com.asiainfo.common.accesstoken.PublicAccessToken;
import com.asiainfo.common.accesstoken.PublicAccessTokenResult;

public interface AccessTokenService {

    PublicAccessToken getPublicAccessToken();

    PublicAccessToken doGetPublicAccessToken(String appId, String appsecret);
}
