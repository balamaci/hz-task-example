package com.fortsoft.hztest.master;

import com.fortsoft.hztest.task.GetWebPageTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.fortsoft.hztask.master.ClusterMaster;
import ro.fortsoft.hztask.master.MasterConfig;
import ro.fortsoft.hztask.master.listener.TaskCompletionListener;
import ro.fortsoft.hztask.master.listener.TaskCompletionListenerFactory;

/**
 * @author Serban Balamaci
 */
public class StartTestMaster {


    public static final Logger log = LoggerFactory.getLogger(StartTestMaster.class);

    public static void main(String[] args) {
        try {
            MasterConfig masterConfig = new MasterConfig();

            masterConfig.registerFinishedTaskProcessorFactory(GetWebPageTask.class, new TaskCompletionListenerFactory() {
                @Override
                public TaskCompletionListener getObject() {
                    return new GetWebPageTaskCompletionListener();
                }
            });
            ClusterMaster clusterMaster = new ClusterMaster(masterConfig, "hzMaster.xml");

            log.info("Submitting a distributed task");
            clusterMaster.submitTask(new GetWebPageTask("https://www.facebook.com"));

//            clusterMaster.shutdown();

            log.info("Program has reached end, bye!");
        } catch (Throwable t) {
            log.error("Uncaught Exception", t);
        }
    }
}
