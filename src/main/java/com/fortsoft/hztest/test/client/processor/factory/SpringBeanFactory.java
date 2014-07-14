package com.fortsoft.hztest.test.client.processor.factory;

import com.fortsoft.hztask.agent.processor.TaskProcessor;
import com.fortsoft.hztask.agent.processor.TaskProcessorFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author Serban Balamaci
 */
public class SpringBeanFactory implements TaskProcessorFactory, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(SpringBeanFactory.class);

    private String beanName;

    private ApplicationContext applicationContext;

    public SpringBeanFactory(String beanName) {
        this.beanName = beanName;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
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
