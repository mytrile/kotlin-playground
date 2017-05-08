package com.kotlinplayground

import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.*

fun main(args: Array<String>) {
    val props = Properties()
    props.put("bootstrap.servers", "localhost:9092")
    props.put("group.id", "test")
    props.put("enable.auto.commit", "true")
    props.put("auto.commit.interval.ms", "1000")
    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    val consumer = KafkaConsumer<String, String>(props)
    consumer.subscribe(Arrays.asList("test"))

    while (true) {
        val records = consumer.poll(100)
        for (record in records)
            System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value())
    }
}