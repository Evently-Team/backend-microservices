package com.evently.user.service.mail;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailDeliveryInteropService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final MailDeliveryConfiguration mailDeliveryConfiguration;

    public void sendMail(MailMessage message) {
        kafkaTemplate.send(mailDeliveryConfiguration.getKafkaTopicName(), message);
    }
}
