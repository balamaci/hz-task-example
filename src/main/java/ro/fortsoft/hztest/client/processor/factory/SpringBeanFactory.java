package ro.fortsoft.hztest.client.processor.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import ro.fortsoft.hztask.agent.processor.TaskProcessor;
import ro.fortsoft.hztask.agent.processor.TaskProcessorFactory;

/**
 * @author Serban Balamaci
 */
public class SpringBeanFactory implements TaskProcessorFactory {

    private static final Logger log = LoggerFactory.getLogger(SpringBeanFactory.class);

    private String beanName;
    private ApplicationContext applicationContext;

    public SpringBeanFactory(ApplicationContext applicationContext, String beanName) {
        this.beanName = beanName;
        this.applicationContext = applicationContext;
    }

    @Override
    public TaskProcessor getObject() {
        try {
            return (TaskProcessor) applicationContext.getBean(beanName);
        } catch (Exception e) {
            log.error("Error creating ProcessorInstance", e);
            throw new RuntimeException("Error creating ProcessorInstance");
        }
    }
}
