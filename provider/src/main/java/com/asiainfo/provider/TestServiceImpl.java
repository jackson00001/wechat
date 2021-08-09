package com.asiainfo.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.asiainfo.api.TestService;

@Service
public class TestServiceImpl implements TestService {

    @Override
    public void doTest() {
        System.out.println("test");
    }
}
