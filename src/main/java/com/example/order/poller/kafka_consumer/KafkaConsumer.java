package com.example.order.poller.kafka_consumer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "ORDER.POLLER.TP",groupId = "group_i")
    public void consumeKafkaMessage(String payload){
        log.info("Consumer Message : {}",payload);
    }
}
