package com.petproject.orderservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации Apache Kafka
 *
 * @author Egor Nazarev
 */
@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic newTopic(){
        return new NewTopic("order-create", 2, (short) 1);
    }
}
