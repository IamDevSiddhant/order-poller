package com.example.order.poller.kafka_producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class KafkaMessagePublisher {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${topic.name}")
    private String topicName;

    public void publishMessage(String payload) {
        log.info("topic name is: {}",topicName);
        //publishing message asynchronously
        CompletableFuture<SendResult<String, String>> send = kafkaTemplate
                .send(topicName, payload);

        //completable future is completed, logging message for our info
        //also checking if any exception occurred.
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + payload +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        payload + "] due to : " + ex.getMessage());
            }

        });

    }
}
