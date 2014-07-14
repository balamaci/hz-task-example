package com.fortsoft.hztest.test.master;

import com.fortsoft.hztask.master.ClusterMaster;
import com.fortsoft.hztask.master.MasterConfig;
import com.fortsoft.hztask.master.processor.FinishedTaskProcessor;
import com.fortsoft.hztask.master.processor.FinishedTaskProcessorFactory;
import com.fortsoft.hztest.test.task.GetWebPageTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Serban Balamaci
 */
public class StartTestMaster {


    public static final Logger log = LoggerFactory.getLogger(StartTestMaster.class);

    public static void main(String[] args) {
        try {
            MasterConfig masterConfig = new MasterConfig();

            masterConfig.registerFinishedTaskProcessorFactory(GetWebPageTask.class, new FinishedTaskProcessorFactory() {
                @Override
                public FinishedTaskProcessor getObject() {
                    return new GetWebPageTaskFinishedProcessor();
                }
            });
            ClusterMaster clusterMaster = new ClusterMaster(masterConfig, "hzMaster.xml");

            log.info("Submitting a distributed task");
            clusterMaster.submitTask(new GetWebPageTask("https://www.facebook.com"));

//            clusterMaster.shutdown();

            log.info("Program has reached end");
        } catch (Throwable t) {
            log.error("Uncaught Exception", t);
        }
    }
}
