package com.ar.sender.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ar.sender.kafka.producer.channel.message.ChannelMessageProducerService;

@Component
public class KafkaMessageScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaMessageScheduler.class);

    private final ChannelMessageProducerService channelMessageProducerService;
    private int counter = 0; // Counter variable


    @Autowired
    public KafkaMessageScheduler(ChannelMessageProducerService channelMessageProducerService) {
        this.channelMessageProducerService = channelMessageProducerService;
    }

    @Scheduled(fixedRate = 5000) // Run every 5 seconds
    public void sendMessage() {
        counter++; // Increment the counter
        String key = "key" + System.currentTimeMillis();
        String value = "Scheduled message at " + System.currentTimeMillis();
        channelMessageProducerService.sendMessage("Test "+counter);
        printMessageWithCurrentTime("Sent scheduled message: key=" + key + ", value=" + value);
    }

    private void printMessageWithCurrentTime(String message) {
        // formate current timestamp in dd/MM/yyyy HH:mm:ss
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String formattedDate = formatter.format(date);
        
        LOGGER.info("At "+ formattedDate + ": " + message);
    }
}
