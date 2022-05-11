package com.gini.kafka.producer;

import com.gini.avro.data.PartPriceUpdate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Slf4j
@Component
@RequiredArgsConstructor
public class UpdatePriceProducerImpl implements UpdatePriceProducer {

    private final KafkaTemplate<String, PartPriceUpdate> kafkaTemplate;

    private ProducerRecord<String, PartPriceUpdate> messageToKafka(PartPriceUpdate partPriceUpdate) {

        String key = partPriceUpdate.getPartId().toString();

        return new ProducerRecord<>("topic_update.price", key, partPriceUpdate);

    }

    @Override
    public void sendMessageToKafka(PartPriceUpdate partPriceUpdate) {
        kafkaTemplate.send(messageToKafka(partPriceUpdate)).addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("error sending message to kafka {}, {}", ex.getMessage(), ex);
            }

            @Override
            public void onSuccess(SendResult<String, PartPriceUpdate> result) {
                log.info("Message was send to kafka -----> with KEY: {} <-->  MESSAGE: {}", result.getProducerRecord().key(), partPriceUpdate);
            }
        });
    }

}
