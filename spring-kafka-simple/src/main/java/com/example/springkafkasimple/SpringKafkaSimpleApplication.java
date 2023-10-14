package com.example.springkafkasimple;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class SpringKafkaSimpleApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringKafkaSimpleApplication.class, args);
    }
}
//listener
@Configuration
class MyKafkaListener{
    public static final String TOPIC_NAME = "myTopic";
    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(TOPIC_NAME)
                .partitions(10)
                .replicas(1)
                .build();
    }
    @KafkaListener(id = "myId", topics = TOPIC_NAME)
    public void listen(String in) {
        System.out.println("Message arrived: " + in);
    }
}

@Controller
@RequestMapping("/kafka")
@RequiredArgsConstructor
class MyController{
    private final MyKafkaSender myKafkaSender;
    @GetMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void sendMessageToMyTopic(){
        myKafkaSender.sendMessageToMyTopic();
    }
}

interface MyKafkaSender {
    void sendMessageToMyTopic();
}

//publisher
@Component
@RequiredArgsConstructor
class MyKafkaSenderImpl implements MyKafkaSender {

    private final KafkaTemplate<String, String> kafkaTemplate;
    @Override
    public void sendMessageToMyTopic() {
        kafkaTemplate.send(MyKafkaListener.TOPIC_NAME, "Hello Kafka World!");
    }
}
