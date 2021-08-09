package com.asiainfo.provider.inter;

import com.alibaba.dubbo.config.annotation.Service;
import com.asiainfo.api.inter.InterService;
import com.asiainfo.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RestTemplateServiceImpl implements InterService {

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String doGet(String url) {
        try {
            ResponseEntity<String> result = restTemplate.getForEntity(new URI(url), String.class);
            if (result.getStatusCode() ==  HttpStatus.OK) {
                return result.getBody();
            } else {
                throw new BusinessException("【" + url + "】调用接口异常，错误状态码为:" + result.getStatusCode().value());
            }
        } catch (URISyntaxException e) {
            throw new BusinessException("【" + url + "】地址无法解析");
        }
    }
}
