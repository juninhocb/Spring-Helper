package com.example.demotwo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.Lifecycle;
import org.springframework.context.SmartLifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

@SpringBootApplication
public class DemoTwoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoTwoApplication.class, args);
    }

}

@RestController
@RequiredArgsConstructor
class MyController{

    private final MyComponentOne myComponentOne;
    private final ApplicationContext ac;

    @GetMapping("/test")
    public Integer getCounter(){
        myComponentOne.addCounter();
        return MyComponentOne.counter;
    }

}

@Component
@RequiredArgsConstructor
class Startup implements CommandLineRunner {

    private final MyComponentThree myComponentThree;
    private final MyComponentFour myComponentFour;
    private final MyComponentFive myComponentFive;
    private final ApplicationContext ctx;

    @Override
    public void run(String... args) throws Exception {
        myComponentThree.printer();
        //myComponentFour.getPhase(); is not smart
        myComponentFive.getPhase();
        MyComponentNine.MyComponentChild obj = (MyComponentNine.MyComponentChild) ctx.getBean("myComponentNine");
        obj.caller();

    }
}
@Configuration
class MyComponentNine implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        return new MyComponentChild();
    }

    @Override
    public Class<?> getObjectType() {
        return MyComponentChild.class;
    }

    @Override
    public boolean isSingleton() {
        return FactoryBean.super.isSingleton();
    }

    class MyComponentChild {
        public void caller() {
            System.out.println("Hello from child!");
        }
    }
}

@Configuration
class MyComponentEight implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        String[] beanNames = beanFactory.getBeanDefinitionNames();

        for (String beanName : beanNames) {
            System.out.println("Bean name: " + beanName);
            if (beanName.equals("messageConverters")){
                //throw new RuntimeException("Error, i do not want to create this bean!");
            }
        }
        System.out.println("Factoring beans " + beanFactory.getBeanPostProcessorCount());
    }
}

//@Configuration
class MyComponentSeven implements BeanPostProcessor, Ordered {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Creating bean " + bean.getClass() + " Name: " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Created bean " + bean.getClass() + " Name: " + beanName);
        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}


@Configuration
class MyConfig {
    @Bean
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyComponentOne getMyComponent(){
        System.out.println("Executed...");
        return new MyComponentOne();
    }

    @Bean(initMethod = "printer", destroyMethod = "bye")
    public MyComponentSix getMySixComponent(){
        return new MyComponentSix();
    }

}

@Component
class MyComponentSix {
    public void printer() {
        System.out.println("Hello from six component!");
    }
    public void bye(){
        System.out.println("Bye from six component");
    }
}

@Component
class MyComponentTwo {

    @PostConstruct
    public void execute(){
        System.out.println("Successfully constructed! - inside creation lock.");
    }

    @EventListener(ContextRefreshedEvent.class)
    public void executeAfter() {
        System.out.println("Executed after bean creation - outside creation lock.");
    }

    @PreDestroy
    public void executeUntilDie(){
        System.out.println("Executed until bean destroy");
    }

}

@Component
class MyComponentThree implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Executed creation. This is not recommended!");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Executed destroy. This is not recommended!");
    }

    public void printer(){
        System.out.println("Hello from three component.");
    }

}

@Component
class MyComponentFour implements Lifecycle {

    @Override
    public void start() {
        System.out.println("Component four lifecycle start");
    }

    @Override
    public void stop() {
        System.out.println("Component four lifecycle stop");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}

@Component
class MyComponentFive implements SmartLifecycle {

    @Override
    public void start() {
        System.out.println("Component five lifecycle start");
    }

    @Override
    public void stop() {
        System.out.println("Component five lifecycle stop");
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}


@RequestScope
@Component
class MyComponentOne{

    @PostConstruct
    public void construct(){
        System.out.println("Executed...");
    }

    public static Integer counter = 0;


    public void addCounter() {
        counter++;
    }

}
