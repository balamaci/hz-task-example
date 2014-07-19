package ro.fortsoft.hztest.master.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.fortsoft.hztask.common.task.Task;
import ro.fortsoft.hztask.master.listener.TaskCompletionHandler;

/**
 * @author Serban Balamaci
 */
public class GetWebPageTaskCompletionHandler extends TaskCompletionHandler {

    private static final Logger log = LoggerFactory.getLogger(GetWebPageTaskCompletionHandler.class);

    @Override
    public void onSuccess(Task task, Object taskResult) {
        log.info("Received {}", taskResult);
    }

    @Override
    public void onFail(Task task, Throwable throwable) {
        log.info("Received Exception for task processing", throwable);
    }
}
