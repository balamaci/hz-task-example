package com.fortsoft.hztest.client.httpclient;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author Serban Balamaci
 */
public class HttpClient4Factory {

    @Value(value = "${http.connection.timeout.ms}")
    private Integer connectionTimeout;

    @Value(value = "${http.socket.timeout.ms}")
    private Integer socketTimeout;

    private PoolingHttpClientConnectionManager defaultConnManager;

    @PostConstruct
    public void init() {
        defaultConnManager = new PoolingHttpClientConnectionManager();
    }

    public HttpClient createDefaultHttpClient() {
        return HttpClientBuilder.create().setConnectionManager(defaultConnManager).build();
    }

    public RequestConfig createRequestConfig() {
        return RequestConfig.custom().
                setConnectTimeout(connectionTimeout).
                setSocketTimeout(socketTimeout).
                setConnectionRequestTimeout(socketTimeout).
                build();
    }

    @PreDestroy
    public void destroy() {
        defaultConnManager.shutdown();
    }
}
