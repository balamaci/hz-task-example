package com.fortsoft.hztest.master;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.fortsoft.hztask.common.task.Task;
import ro.fortsoft.hztask.master.listener.TaskCompletionListener;

/**
 * @author Serban Balamaci
 */
public class GetWebPageTaskCompletionListener extends TaskCompletionListener {

    private static final Logger log = LoggerFactory.getLogger(GetWebPageTaskCompletionListener.class);

    @Override
    public void onSuccess(Task task, Object taskResult) {
        log.info("Received {}", taskResult);
    }

    @Override
    public void onFail(Task task, Throwable throwable) {
        log.info("Received Exception for task processing", throwable);
    }
}
