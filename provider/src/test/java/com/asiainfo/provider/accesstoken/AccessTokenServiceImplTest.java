package com.asiainfo.provider.accesstoken;

import com.alibaba.fastjson.JSON;
import com.asiainfo.common.accesstoken.PublicAccessTokenResult;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccessTokenServiceImplTest {

    @Test
    public void fastJsonTest() {
        PublicAccessTokenResult publicAccessTokenResult = JSON.parseObject(null, PublicAccessTokenResult.class);
        System.out.println(publicAccessTokenResult); // null
    }

}