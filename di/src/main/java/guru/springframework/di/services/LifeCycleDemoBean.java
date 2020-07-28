package guru.springframework.di.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class LifeCycleDemoBean implements InitializingBean, DisposableBean, BeanNameAware,
        BeanFactoryAware, ApplicationContextAware {

    /* Create */

    public LifeCycleDemoBean() {
        System.out.println("## I'm in the LifeCycleBean Constructor");
    }

    // from BeanNameAware
    @Override
    public void setBeanName(String name) {
        System.out.println("## My Bean name is: " + name);
    }

    // from BeanFactoryAware
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("## BeanFactory has been set");
    }

    // from ApplicationContextAware
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("## ApplicationContext has been set");
    }

    public void beforeInit(){
        System.out.println("## - Before Init - Called by Bean Post Processor");
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("## The Post Construct annotated method has been called");
    }

    // from InitializingBean
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("## The LifeCycleBean has its properties set");
    }

    public void afterInit(){
        System.out.println("## - After init - called by Bean Post Processor");
    }


    /* Destroy */

    @PreDestroy
    public void preDestroy() {
        System.out.println("## The Pre Destroy annotated method has been called");
    }

    // from DisposableBean
    @Override
    public void destroy() throws Exception {
        System.out.println("## The LifeCycleBean has been terminated");
    }
}
