package com.fortsoft.hztest.test.master;

import com.fortsoft.hztask.common.task.Task;
import com.fortsoft.hztask.master.processor.FinishedTaskProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Serban Balamaci
 */
public class GetWebPageTaskFinishedProcessor extends FinishedTaskProcessor {

    private static final Logger log = LoggerFactory.getLogger(GetWebPageTaskFinishedProcessor.class);

    @Override
    public void processSuccessful(Task task, Object taskResult) {
        log.info("Received {}", taskResult);
    }

    @Override
    public void processFailed(Task task, Throwable throwable) {
        log.info("Received Exception for task processing", throwable);
    }
}
