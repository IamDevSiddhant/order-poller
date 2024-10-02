package com.example.order.poller.service;

import com.example.order.poller.entity.OrderOutBox;
import com.example.order.poller.kafka_producer.KafkaMessagePublisher;
import com.example.order.poller.repository.OutBoxRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@EnableScheduling
public class OrderPollerService {

    @Autowired
    private KafkaMessagePublisher kafkaMessagePublisher;

    @Autowired
    private OutBoxRepository outBoxRepository;

    @Scheduled(fixedRate = 60000)
    public void pollOutBoxAndPublishMessages(){
        List<OrderOutBox> unprocessedOrders = outBoxRepository.findByProcessedFalse();
        log.info("size of unprocessed orders : {}",unprocessedOrders.size());
        unprocessedOrders.forEach(orderOutBox ->{
            try {
                kafkaMessagePublisher.publishMessage(orderOutBox.getPayload());
                orderOutBox.setProcessed(true);
                outBoxRepository.save(orderOutBox);
            }catch (Exception e){
                log.info("exception : {}",e);
            }
        });
    }

}
