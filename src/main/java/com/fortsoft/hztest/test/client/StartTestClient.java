package com.fortsoft.hztest.test.client;

import com.fortsoft.hztask.agent.AgentConfig;
import com.fortsoft.hztask.agent.ClusterAgent;
import com.fortsoft.hztask.agent.processor.TaskProcessorFactory;
import com.fortsoft.hztest.test.task.GetWebPageTask;
import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryXmlConfig;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author Serban Balamaci
 */
public class StartTestClient {

    public static final Logger log = LoggerFactory.getLogger(StartTestClient.class);


    public static void main(String[] args) {
        try {
            AgentConfig agentConfig = new AgentConfig();

            ApplicationContext context =
                    new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});
            String hzConfigXml = IOUtils.toString(context.getResource("hzAgent.xml").getInputStream());

            agentConfig.registerTaskProcessorFactory(GetWebPageTask.class,
                    (TaskProcessorFactory) context.getBean("webPageRequestProcessorFactory"));


            Config hazelcastConfig = new InMemoryXmlConfig(hzConfigXml);

            ClusterAgent clusterAgent = new ClusterAgent(agentConfig, hazelcastConfig);

            log.info("Program has reached end");
        } catch (Throwable t) {
            log.error("Uncaught Exception", t);
        }
    }

}
