package com.fortsoft.hztest.task;


import ro.fortsoft.hztask.common.task.Task;

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
