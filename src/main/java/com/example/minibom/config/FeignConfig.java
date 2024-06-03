package com.example.minibom.config;

/*
 * Copyright (c) Huawei Technologies Co., Ltd. 2024-2024. All rights reserved.
 */

import com.huawei.innovation.rdm.delegate.exception.RdmDelegateException;

import feign.Client;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * feign配置
 *
 * @since 2024-04-10
 */
@Configuration
public class FeignConfig {
    /**
     * 初始化 feignClient 对象，忽略证书校验
     *
     * @return bean对象
     */
    @Bean
    public Client feignClient() {
        // 如果不涉及代理服务器，需要去除代理服务器配置项，更换实现方式
        return new Client.Default(getTrustingSSLSocketFactory(), new NoopHostnameVerifier());
//        return new Client.Proxied(getTrustingSSLSocketFactory(), NoopHostnameVerifier.INSTANCE,
//                new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.huawei.com", 8080)));
    }

    private SSLSocketFactory getTrustingSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, getTrustManager(), new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            throw new RdmDelegateException("config.1", e.getMessage());
        }
    }

    private TrustManager[] getTrustManager() {
        return new TrustManager[] {
                new X509TrustManager() {
                    /**
                     * 获取证书颁发者列表
                     *
                     * @return 证书颁发者列表
                     */
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    /**
                     * 校验客户端证书
                     *
                     * @param certs the peer certificate chain
                     * @param authType the authentication type based on the client certificate
                     */
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    /**
                     * 校验服务端证书
                     *
                     * @param certs the peer certificate chain
                     * @param authType the key exchange algorithm used
                     */
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                }
        };
    }
}

