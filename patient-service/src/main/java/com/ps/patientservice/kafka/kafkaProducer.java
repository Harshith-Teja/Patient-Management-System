package com.ps.patientservice.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class kafkaProducer {

    private final KafkaTemplate<String, Byte[]> kafkaTemplate;

    public kafkaProducer(KafkaTemplate<String, Byte[]> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
}
