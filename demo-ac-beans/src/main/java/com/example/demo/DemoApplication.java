package com.example.demo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }

}

@Configuration
@RequiredArgsConstructor
class AppContext implements CommandLineRunner {

    private final ApplicationContext ac;
    private String splitter = "\n";

    @Override
    public void run(String... args) throws Exception {

        System.out.println(ac.getBeanDefinitionCount() + splitter);

        MyComponentOne one =  ac.getBean("myComponentOne", MyComponentOne.class);

        System.out.println(one.getGreeting());

        SingletonBeanRegistry beanRegistry = (SingletonBeanRegistry) ac.getAutowireCapableBeanFactory();

        MyComponentTwo myComponentTwo = new MyComponentTwo();

        beanRegistry.registerSingleton("myComponentTwo", myComponentTwo);

        MyComponentTwo two = ac.getBean(MyComponentTwo.class);

        two.getGreetings();

        MyComponentThreeFactory factory = new MyComponentThreeFactory();

        beanRegistry.registerSingleton("test",  factory.getObject());

        MyComponentThree three = ac.getBean("test", MyComponentThree.class);

        three.getGreetings();

        /*DefaultListableBeanFactory dlbf = (DefaultListableBeanFactory) ac.getParentBeanFactory();

        BeanDefinition beanDefinitionThree = dlbf.getBeanDefinition("test");

        System.out.println(beanDefinitionThree.getBeanClassName()
                + splitter + beanDefinitionThree.getFactoryBeanName()
                + splitter + beanDefinitionThree.getDescription()
                + splitter + beanDefinitionThree.getBeanClassName()
                + splitter + beanDefinitionThree.getInitMethodName()
                + splitter + beanDefinitionThree.getScope());*/

        MyComponentFour four = ac.getBean("stub_four", MyComponentFour.class);
        assert(four != null);
        MyComponentFive five = ac.getBean("myComponentFive", MyComponentFive.class);
        five.done();


    }
}


class MyComponentThree {

    private static Integer counter= 0;
    public MyComponentThree(){
        counter++;
    }
    public void getGreetings() {
        System.out.println("Greetings from Component three counter = " + counter);
    }
}

class MyComponentThreeFactory implements FactoryBean<MyComponentThree> {

    @Override
    public MyComponentThree getObject() throws Exception {
        return new MyComponentThree();
    }

    @Override
    public Class<?> getObjectType() {
        return MyComponentThree.class;
    }

    @Override
    public boolean isSingleton() {
        //return FactoryBean.super.isSingleton();
        return false;
    }
}

@Controller
@RequiredArgsConstructor
class MyEndPoint {
    private final MyComponentOne myComponentOne;
    private final BeanFactory beanFactory;

    @GetMapping("/api")
    public ResponseEntity<Map<Integer, Integer>> getCounter() {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(myComponentOne.getCount(), MyComponentOne.staticCounter);
        return ResponseEntity.ok(map);
    }

    @PostMapping("/post")
    public ResponseEntity<String> processVoucher(@RequestBody Voucher voucher) {
        if (voucher.code() == 0) {
            return ResponseEntity.unprocessableEntity().build();
        }
        String message = String.format("Success processed. Id [ %s ] Code [ %d ] at  %s" , voucher.id(), voucher.code(), new Date());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/exception")
    public void runtimeExceptionHandler() {
        throw new RuntimeException("I was called but not handled");
    }

    @GetMapping("/get-bean")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void getBean() {
        MyComponentTwo two =  beanFactory.getBean("myComponentTwo", MyComponentTwo.class);
        two.getGreetings();
        MyComponentThree three = beanFactory.getBean("test", MyComponentThree.class);
        three.getGreetings();
    }

}

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageException> runtimeHandler(RuntimeException rte, HttpServletRequest request) {
        MessageException me = MessageException.builder()
                .timestamp(new Date().toString())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .error("Error with processing")
                .message(rte.getMessage().replace("not", "perfectly"))
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.badRequest().body(me);
    }
}

@Builder
record MessageException(String timestamp, @JsonProperty("status") Integer statusCode, String error, String message, String path){}
record Voucher (String id, Integer code) {}

@Configuration
@Import(AnotherConfig.class)
class MyConfigClass {

    @Bean
    //@Scope(value = "request" , proxyMode = ScopedProxyMode.TARGET_CLASS)
    public MyComponentOne myComponentOne() {
        return new MyComponentOne();
    }

    @Bean(value = "stub_four", initMethod = "execute")
    public MyComponentFour myComponentFour(){
        return new MyComponentFour();
    }

}

class AnotherConfig {

    @Bean
    public MyComponentFive myComponentFive(){
        return new MyComponentFive();
    }

}

class MyComponentFive {
    public void done(){
        System.out.println("done!");
    }
}
class MyComponentFour {
    public void execute(){
        System.out.println("Doing execute");
    }
}

class MyComponentTwo {

    private static Integer counter = 0;

    public MyComponentTwo(){
        counter++;
    }

    public void getGreetings() {
        System.out.println("Hello from component two counter  = " + counter);
    }

}

class MyComponentOne {
    private Integer count = 0 ;
    public static Integer staticCounter= 0;

    private String greeting = "Greetings from My Component One";

    MyComponentOne() {
        count ++;
        staticCounter ++;
    }
    public Integer getCount () {
        return this.count;
    }

    public String getGreeting() {
        return this.greeting;
    }

}
