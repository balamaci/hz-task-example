package ro.fortsoft.hztest.master;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ro.fortsoft.hztask.master.ClusterMaster;
import ro.fortsoft.hztask.master.MasterConfig;
import ro.fortsoft.hztask.master.listener.TaskCompletionHandler;
import ro.fortsoft.hztask.master.listener.TaskCompletionHandlerFactory;
import ro.fortsoft.hztest.master.handler.GetWebPageTaskCompletionHandler;
import ro.fortsoft.hztest.task.EchoTask;
import ro.fortsoft.hztest.task.GetWebPageTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author Serban Balamaci
 */
public class StartTestMaster {


    public static final Logger log = LoggerFactory.getLogger(StartTestMaster.class);

    public static void main(String[] args) {
        try {
            MasterConfig masterConfig = new MasterConfig();

            masterConfig.registerFinishedTaskCompletionListenerFactory(GetWebPageTask.class, new TaskCompletionHandlerFactory() {
                @Override
                public TaskCompletionHandler getObject() {
                    return new GetWebPageTaskCompletionHandler();
                }
            });
            ClusterMaster clusterMaster = new ClusterMaster(masterConfig, "hzMaster.xml");

            log.info("Submitting a distributed task");
//            clusterMaster.submitTask(new GetWebPageTask("https://www.facebook.com"));
            addEchoTasks(1000, clusterMaster);
        } catch (Throwable t) {
            log.error("Uncaught Exception", t);
        }
    }

    private static void addEchoTasks(int size, ClusterMaster clusterMaster) {
        for(int i=0; i < size; i++) {
            clusterMaster.submitTask(new EchoTask(i));
        }
    }

    private static void readInput() throws Exception {
        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(isr);
        String line = null;
/*
        while ((line = reader.readLine()) != null) {
            if() {

            }
            // Process line
        }
*/
    }

}
