package com.gini.kafka.producer;

import com.gini.avro.data.PartPriceUpdate;

public interface UpdatePriceProducer {
    void sendMessageToKafka(PartPriceUpdate partPriceUpdate);
}
