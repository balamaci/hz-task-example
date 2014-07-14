package com.fortsoft.hztest.test.task;

import com.fortsoft.hztask.common.task.Task;

/**
 * @author Serban Balamaci
 */
public class GetWebPageTask extends Task {

    private final String pageUrl;

    public GetWebPageTask(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }
}
