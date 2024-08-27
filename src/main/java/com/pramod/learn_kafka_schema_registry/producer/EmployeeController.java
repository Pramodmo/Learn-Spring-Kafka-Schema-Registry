package com.pramod.learn_kafka_schema_registry.producer;

import com.pramod.learn_kafka_schema_registry.models.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/employee")
public class EmployeeController {

    private  KafkaProducer producer;

    public EmployeeController(KafkaProducer producer) {
        this.producer = producer;
    }

    @PostMapping("/publish")
    public String publishEmployee(@RequestBody Employee employee){
        producer.send(employee);
        return "Message published successfully !";
    }

}
