package ro.fortsoft.hztest.client.processor;

import ro.fortsoft.hztask.agent.processor.TaskProcessor;
import ro.fortsoft.hztask.common.task.Task;
import ro.fortsoft.hztest.task.EchoTask;

import java.io.Serializable;

/**
 * @author Serban Balamaci
 */
public class EchoTaskProcessor extends TaskProcessor {

    @Override
    public Serializable process(Task task) {
        EchoTask echoTask = (EchoTask) task;
        System.out.println("Output " + (echoTask).getCounter());

        try {
            if(echoTask.getCounter() > 500) {
//                Thread.sleep(3000);
                throw new RuntimeException("Simulated exception");
            } else {
                Thread.sleep(2000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
