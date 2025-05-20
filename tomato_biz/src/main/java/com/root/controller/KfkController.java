package com.root.controller;


import com.root.component.Producer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webSocket")
public class KfkController {
    @Autowired
    Producer producer;
    /**
     *
     */
    @RequestMapping(value = "/sendKafkaAllMessage", method = RequestMethod.GET)
    public RecordMetadata sendAllMessage(@RequestParam(required = true) String message) {
        RecordMetadata recordMetadata= producer.sendChannelMess("test13",message);
        return recordMetadata;

    }

}
