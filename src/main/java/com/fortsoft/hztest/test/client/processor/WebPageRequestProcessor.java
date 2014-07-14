package com.fortsoft.hztest.test.client.processor;

import com.fortsoft.hztask.agent.processor.TaskProcessor;
import com.fortsoft.hztask.common.task.Task;
import com.fortsoft.hztest.test.task.GetWebPageTask;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author Serban Balamaci
 */
public class WebPageRequestProcessor extends TaskProcessor<String> {

    @Autowired
    private HttpClient httpClient;

    @Autowired
    private RequestConfig requestConfig;

    @Override
    public String process(Task task) {
        GetWebPageTask getWebPageTask = (GetWebPageTask) task;

        HttpGet httpGet = new HttpGet(getWebPageTask.getPageUrl());
        httpGet.setConfig(requestConfig);

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
            String responseString = IOUtils.toString(response.getEntity().getContent());

            return responseString;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

    public void setHttpClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
