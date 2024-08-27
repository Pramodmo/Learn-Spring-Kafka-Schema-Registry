package com.pramod.learn_kafka_schema_registry.consumer;

import com.pramod.learn_kafka_schema_registry.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "${kafka.topic.name}")
    public void consumeEmployeeEvent(ConsumerRecord<String,Employee> employeeRecord){
      log.info("Consumer consumed the employee event with key : {}  and  value : {}",employeeRecord.key(), employeeRecord.value());
    }

}
