package com.kotlinplayground


import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.Producer
import org.apache.kafka.clients.producer.ProducerConfig

import java.util.Properties
import com.sun.xml.internal.ws.streaming.XMLStreamReaderUtil.close
import org.apache.kafka.clients.producer.ProducerRecord

fun main(args: Array<String>) {
    val properties = Properties()

    properties.put("bootstrap.servers", "localhost:9092");
    properties.put("acks", "all");
    properties.put("retries", 0);
    properties.put("batch.size", 16384);
    properties.put("linger.ms", 1);
    properties.put("buffer.memory", 33554432);
    properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
    properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

    val producer = KafkaProducer<String, String>(properties)

    for (i in 1..10_000)
        producer.send(ProducerRecord("test", "key$i", "Message $i"))

    producer.close()
}
