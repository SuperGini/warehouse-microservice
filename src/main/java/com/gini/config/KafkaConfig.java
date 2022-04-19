package com.gini.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic test(){
        return TopicBuilder
                .name("topic_update.price")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic test2(){
        return TopicBuilder
                .name("topic_update.price.validation.unit")
                .partitions(2)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic test3(){
        return TopicBuilder
                .name("topic_replay.warehouse")
                .partitions(1)
                .replicas(1)
                .build();
    }
}
