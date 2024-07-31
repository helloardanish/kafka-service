package com.ar.sender.kafka.producer.channel.message;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChannelMessageProducerService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChannelMessageProducerService.class);
    
    private final KafkaTemplate<String, String> kafkaTemplate;


    @Value("${kafka.topic}")
    private String topic;

    @Autowired
    public ChannelMessageProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String value) {
        kafkaTemplate.send(topic, "message", value);
        LOGGER.info("Sent message: key=message, value=" + value);
    }

    public void sendMessage(String key, String value) {
        kafkaTemplate.send(topic, key, value);
        LOGGER.info("Sent message: key=" + key + ", value=" + value);
    }

}
