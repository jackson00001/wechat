package com.asiainfo.provider.accesstoken;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.asiainfo.api.accesstoken.AccessTokenService;
import com.asiainfo.api.inter.InterService;
import com.asiainfo.api.redis.RedisService;
import com.asiainfo.common.Constant;
import com.asiainfo.common.accesstoken.PublicAccessToken;
import com.asiainfo.common.accesstoken.PublicAccessTokenResult;
import com.asiainfo.common.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.HashMap;
import java.util.Map;

@Service
public class AccessTokenServiceImpl implements AccessTokenService {

    @Value("${wechat.appId}")
    private String appId;

    @Value("${wechat.appsecret}")
    private String appsecret;

    @Autowired
    private RedisService redisService;

    @Autowired
    private InterService interService;

    @Override
    public PublicAccessToken getPublicAccessToken() {
        String accessToken = redisService.get(Constant.AccessToken.PUBLIC_ACCESS_TOKEN_REDIS);
        if (StringUtils.isBlank(accessToken)
                && redisService.lock(Constant.AccessToken.PUBLIC_ACCESS_TOKEN_REDIS)) {
            PublicAccessToken publicAccessToken = doGetPublicAccessToken(appId, appsecret);
            redisService.setExpire(Constant.AccessToken.PUBLIC_ACCESS_TOKEN_REDIS, publicAccessToken.getAccessToken(), Constant.AccessToken.EXPIRE_TIME);
            redisService.unlock(Constant.AccessToken.PUBLIC_ACCESS_TOKEN_REDIS);
            return publicAccessToken;
        }
        long expireTime = redisService.ttl(Constant.AccessToken.PUBLIC_ACCESS_TOKEN_REDIS);
        return new PublicAccessToken(accessToken, expireTime);
    }

    @Override
    public PublicAccessToken doGetPublicAccessToken(String appId, String appsecret) {
        Map<String, String> replaceMap = new HashMap<>();
        replaceMap.put("myGrantType", "client_credential");
        replaceMap.put("myAppId", appId);
        replaceMap.put("mySecret", appsecret);
        String json = interService.doGet(replaceUrlParam(Constant.AccessToken.Url.PUBLIC_ACCESS_TOKEN_URL, replaceMap));
        PublicAccessTokenResult publicAccessTokenResult = JSON.parseObject(json, PublicAccessTokenResult.class);
        if (StringUtils.isNotBlank(publicAccessTokenResult.getErrcode())) {
            throw new BusinessException("微信返回信息异常:【" + publicAccessTokenResult.getErrcode() + "】"
                    + publicAccessTokenResult.getErrmsg());
        }
        return publicAccessTokenResult.getPublicAccessToken();
    }

    private String replaceUrlParam(String url, Map<String, String> replaceMap) {
        for (String key : replaceMap.keySet()) {
            url = url.replace(key, replaceMap.get(key));
        }
        return url;
    }
}
