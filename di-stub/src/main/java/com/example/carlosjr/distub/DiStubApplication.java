package com.example.carlosjr.distub;

import com.example.carlosjr.distub.controllers.ConstructorInjectedController;
import com.example.carlosjr.distub.controllers.MyController;
import com.example.carlosjr.distub.controllers.PropertyInjectedController;
import com.example.carlosjr.distub.controllers.SetterInjectedController;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DiStubApplication {

    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(DiStubApplication.class, args);
        MyController myController = (MyController)  context.getBean("myController");

        String greeting = myController.sayHello();
        System.out.println(greeting);

        PropertyInjectedController propertyInjectedController =
                (PropertyInjectedController) context.getBean("propertyInjectedController");

        System.out.println(propertyInjectedController.getGreeting());

        SetterInjectedController setterInjectedController
                = (SetterInjectedController) context.getBean("setterInjectedController");

        System.out.println(setterInjectedController.getGreetingService().sayGreetings());

        ConstructorInjectedController constructorInjectedController
                = (ConstructorInjectedController) context.getBean("constructorInjectedController");

        System.out.println(constructorInjectedController.getGreeting());

     }

}
