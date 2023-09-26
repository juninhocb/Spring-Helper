package com.example.demo3annotationbased;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Priority;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@SpringBootApplication
public class Demo3AnnotationBasedApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo3AnnotationBasedApplication.class, args);
    }

}

//@Component
class StartupThree implements CommandLineRunner {

    private final MyPrinterComponent myPrinterComponent;

    public StartupThree (@Qualifier("myComponentFive") MyPrinterComponent myPrinterComponent,
                         @Autowired(required = false) MyComponentSix myComponentSix) {
        this.myPrinterComponent = myPrinterComponent;
        if (myComponentSix != null){
            myComponentSix.execute();
        }

    }

    @Override
    public void run(String... args) throws Exception {
        myPrinterComponent.printHello();
    }
}

//@Component
class StartupTwo implements CommandLineRunner {

    @Autowired
    private MyPrinterComponent[] myPrinterComponents;

    @Override
    public void run(String... args) throws Exception {
        myPrinterComponents[0].printHello();
        myPrinterComponents[1].printHello();
        myPrinterComponents[2].printHello();
    }
}

//@Component
class Startup implements CommandLineRunner {

    @PostConstruct
    public void postExecutor() {
        this.myComponentFour = new MyComponentFour();
    }

    private MyComponentOne myComponentOne;
    private MyComponentTwo myComponentTwo;
    private MyComponentFour myComponentFour;

    //field
    @Autowired
    public MyComponentThree myComponentThree;

    public Startup(){

    }

    //constructor
    @Autowired
    public Startup(MyComponentOne myComponentOne) {
        this.myComponentOne = myComponentOne;
    }

    //method
    @Autowired
    public void autowireMethod(MyComponentTwo myComponentTwo){
        this.myComponentTwo = myComponentTwo;
    }

    @Override
    public void run(String... args) throws Exception {
        this.myComponentOne.printGreeting();
        this.myComponentTwo.printHello();
        this.myComponentThree.printHello();
        this.myComponentFour.printHello();
    }
}

@Configuration
class BasicConfig {

    @Bean
    @Order(value = 3)
    //Your effect may be override by @Qualifier annotation
    @Primary
    public MyComponentTwo myComponentTwo(){
        return new MyComponentTwo();
    }

    @Bean
    @Order(value = 1)
    public MyComponentFive myComponentFive() {
        return new MyComponentFive();
    }

    @Bean(value = "comedy", initMethod = "initMoviesMethod", destroyMethod = "destroyMoviesMethod")
    public MoviesComedyCatalog moviesComedyCatalog() {
        return new MoviesComedyCatalog();
    }
}

//@Component
class StartupFour implements CommandLineRunner {

    private final MoviesCatalogs moviesCatalogs;


    StartupFour(/*@Qualifier("comedy")*//*@Qualifier("generic")*//*@Genre("sports")*/@MovieQualifier(genre = "love", format = MovieFormat.NETFLIX) MoviesCatalogs moviesCatalogs) {
        this.moviesCatalogs = moviesCatalogs;
    }

    @Override
    public void run(String... args) throws Exception {
        moviesCatalogs.printMovie();
    }
}

//@Component
@PropertySource("classpath:my.properties")
class StartupFive implements CommandLineRunner{

    @Value("${foo.bar}")
    private String myFooBar;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(myFooBar);
    }
}

@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@interface Genre {
    String value();
}

@Target({ElementType.TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@Component
@interface MovieQualifier {
    MovieFormat format() default MovieFormat.DVD;
    String genre() default "generic";
}

enum MovieFormat {
    VHS, DVD, NETFLIX
}

class MoviesComedyCatalog implements MoviesCatalogs {
    @Override
    public void printMovie() {
        System.out.println("Movie Comedy 1 - Movie Comedy 2");
    }

    public void initMoviesMethod(){
        System.out.println("Startup successfully");
    }

    @PostConstruct
    public void postExecute(){
        System.out.println("Bean completed!");
    }

    public void destroyMoviesMethod(){
        System.out.println("Destroy!");
    }

}

@Component("generic")
@MovieQualifier(format = MovieFormat.VHS)
class MoviesGenericCatalog implements MoviesCatalogs {
    @Override
    public void printMovie() {
        System.out.println("Movie Generic 1 - Movie Generic 2 - Movie Generic 3");
    }
}
@MovieQualifier(format = MovieFormat.NETFLIX, genre = "love")
class MoviesLoveCatalog implements MoviesCatalogs{
    @Override
    public void printMovie() {
        System.out.println("Movie Love 1 ");
    }
}

@Component("sports")
class MoviesSportsCatalog implements MoviesCatalogs{
    @Override
    public void printMovie() {
        System.out.println("Movie Sports 1 - Movies Sports 2");
    }
}

interface MoviesCatalogs {
    void printMovie();
}

//@Component
class MyComponentSix{
    public void execute(){
        System.out.println("Executing");
    }
}

class MyComponentFour{
    public void printHello(){
        System.out.println("Hello from component four!");
    }

}
@Component
//@Order(value = 2)
//make three the first and primary candidate (the second behaviour may be override by
//primary or qualifier annotations
@Priority(value = 1)
class MyComponentThree implements MyPrinterComponent{
    public void printHello(){
        System.out.println("Hello from component three!");
    }
}

class MyComponentFive implements MyPrinterComponent {

    @Override
    public void printHello() {
        System.out.println("Hello from component five");
    }
}
class MyComponentTwo implements MyPrinterComponent{

    public void printHello() {
        System.out.println("Hello from component two!");
    }

}

@Component
class MyComponentOne{
    private final String greeting;

    MyComponentOne() {
       this.greeting  = "Hello from my component one!";
    }

    public void printGreeting() {
        System.out.println(this.greeting);
    }
}

interface MyPrinterComponent{
    void printHello();
}