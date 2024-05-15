package com.martinesco.br.kafka.example.domain.services;

import com.martinesco.br.kafka.example.dtos.EmailDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    private final KafkaTemplate<String, EmailDTO> kafkaTemplate;

    public NotificationService(KafkaTemplate<String, EmailDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEmail(EmailDTO emailDTO) {
        kafkaTemplate.send("emails", emailDTO);
    }
}
