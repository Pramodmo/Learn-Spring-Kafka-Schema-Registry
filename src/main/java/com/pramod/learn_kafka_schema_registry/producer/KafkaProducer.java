package com.pramod.learn_kafka_schema_registry.producer;

import com.pramod.learn_kafka_schema_registry.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
public class KafkaProducer {

    @Value("${kafka.topic.name}")
    private String topicName;

    private final KafkaTemplate<String,Employee> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, Employee> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(Employee employee) {
        CompletableFuture<SendResult<String, Employee>> future = kafkaTemplate.send(topicName, UUID.randomUUID().toString(), employee);
        future.whenComplete((result, ex) -> {
            if (ex != null){
                System.out.println("Unable to send message=["+employee.toString() + "] due to :" + ex.getMessage());
                log.error("Exception occurred while sending message", ex);
            } else {
                System.out.println("Sent message=[" + employee.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() +"]");
                log.info("Message sent successfully with result: {}", result);
            }

        });
    }
}
