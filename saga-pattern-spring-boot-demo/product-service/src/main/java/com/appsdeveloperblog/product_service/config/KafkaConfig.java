package com.appsdeveloperblog.product_service.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaConfig {

  @Value("${products.events.topic.name}")
  private String productsEventsTopic;

  private static final Short TOPIC_REPLICATION_FACTOR = 3;
  private static final Integer TOPIC_PARTITIONS = 3;

  @Bean
  KafkaTemplate<String, Object> kafkaTemplate(ProducerFactory<String, Object> producerFactory) {
    return new KafkaTemplate<>(producerFactory);
  }

  @Bean
  NewTopic productsEventsTopic() {
    return TopicBuilder.name(productsEventsTopic)
        .partitions(TOPIC_PARTITIONS)
        .replicas(TOPIC_REPLICATION_FACTOR)
        .compact()
        .build();
  }
}
