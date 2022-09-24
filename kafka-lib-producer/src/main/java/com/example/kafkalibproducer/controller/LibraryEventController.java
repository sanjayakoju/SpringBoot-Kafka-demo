package com.example.kafkalibproducer.controller;

import com.example.kafkalibproducer.domain.LibraryEvent;
import com.example.kafkalibproducer.enums.LibraryEventType;
import com.example.kafkalibproducer.producer.LibraryEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.support.SendResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/v1/libraryevent")
@Slf4j
public class LibraryEventController {

    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping()
    public ResponseEntity<?> postLibraryEvent(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException, ExecutionException, InterruptedException {
        libraryEvent.setLibraryEventType(LibraryEventType.NEW);
//        libraryEventProducer.sendLibraryEvent(libraryEvent);
//        SendResult<Integer,String> sendResult = libraryEventProducer.sendLibraryEventSynchronous(libraryEvent);
        libraryEventProducer.sendLibraryEventSynchronousApproach2(libraryEvent);
//        log.info("Send Result {}",sendResult.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateLibraryEvent(@RequestBody @Validated LibraryEvent libraryEvent) throws JsonProcessingException {
        if (libraryEvent.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please pass the id");
        }
        libraryEvent.setLibraryEventType(LibraryEventType.UPDATE);
        libraryEventProducer.sendLibraryEventSynchronousApproach2(libraryEvent);
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
