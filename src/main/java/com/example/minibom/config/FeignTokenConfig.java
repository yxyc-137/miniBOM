package com.example.minibom.config;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import com.huawei.innovation.rdm.delegate.common.XdmDelegateConsts;
import com.huawei.innovation.rdm.delegate.service.XdmTokenService;

import feign.RequestInterceptor;
import feign.RequestTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

/**
 * 配置请求头信息
 *
 * @since 2024-04-10
 */
@Configuration
public class FeignTokenConfig implements RequestInterceptor {
    @Autowired
    private XdmTokenService tokenService;

    @Override
    public void apply(RequestTemplate template) {
        template.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        template.header(XdmDelegateConsts.X_AUTH_TOKEN, tokenService.getToken());
    }
}


