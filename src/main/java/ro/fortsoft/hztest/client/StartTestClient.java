package ro.fortsoft.hztest.client;

import com.hazelcast.config.Config;
import com.hazelcast.config.InMemoryXmlConfig;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ro.fortsoft.hztask.agent.AgentConfig;
import ro.fortsoft.hztask.agent.ClusterAgent;
import ro.fortsoft.hztest.client.processor.factory.SpringBeanFactory;
import ro.fortsoft.hztest.task.EchoTask;
import ro.fortsoft.hztest.task.GetWebPageTask;

/**
 * @author Serban Balamaci
 */
public class StartTestClient {

    public static final Logger log = LoggerFactory.getLogger(StartTestClient.class);


    public static void main(String[] args) {
        try {

            ApplicationContext context =
                    new ClassPathXmlApplicationContext(new String[]{ "applicationContext.xml"});


            String hzConfigXml = IOUtils.toString(context.getResource("hzAgent.xml").getInputStream());
            Config hazelcastConfig = new InMemoryXmlConfig(hzConfigXml);

            AgentConfig agentConfig = new AgentConfig();

            agentConfig.registerTaskProcessorFactory(GetWebPageTask.class,
                    new SpringBeanFactory(context, "webPageRequestTaskProcessor"));
            agentConfig.registerTaskProcessorFactory(EchoTask.class,
                    new SpringBeanFactory(context, "echoTaskProcessor"));

            new ClusterAgent(agentConfig, hazelcastConfig);

        } catch (Throwable t) {
            log.error("Uncaught Exception", t);
        }
    }

}
