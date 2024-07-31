package com.ar.receiver_one.kafka.consumer.channel.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ChannelMessageConsumerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelMessageConsumerService.class);

    @KafkaListener(topics = "${kafka.topic}", groupId = "${kafka.consumer.group-id}")
    public void listen(String message) {
        LOGGER.info("Received message: " + message);
    }
}
