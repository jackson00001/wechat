package com.asiainfo.common;

public class Constant {

    public interface Flag {
        String SUCCESS = "success";
        String FAIL = "fail";
    }

    public interface AccessToken {

        // 一个半小时
        int EXPIRE_TIME = 5400;

        String PUBLIC_ACCESS_TOKEN_REDIS = "publicAccessToken";

        interface Url {
            String PUBLIC_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=myGrantType&appid=myAppId&secret=mySecret";
        }
    }
}
