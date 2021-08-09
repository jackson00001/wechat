package com.asiainfo.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.asiainfo.api.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference
    private TestService testService;

    @GetMapping("/test01")
    public void test01(){
        testService.doTest();
    }
}
