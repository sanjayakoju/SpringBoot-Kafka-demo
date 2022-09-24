package com.example.kafkalibconsumer.service;

import com.example.kafkalibconsumer.entity.LibraryEvent;
import com.example.kafkalibconsumer.repository.LibraryEventRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LibraryService {

    @Autowired
    LibraryEventRepository libraryEventRepository;

    @Autowired
    ObjectMapper objectMapper;

    public void processLibraryEvent(ConsumerRecord<Integer, String> consumerRecord) throws JsonProcessingException {
       LibraryEvent libraryEvent = objectMapper.readValue(consumerRecord.value(), LibraryEvent.class);
       log.info("LibraryEvent {} ",libraryEvent);

       switch (libraryEvent.getLibraryEventType()) {
           case NEW:
               save(libraryEvent);
               break;
           case UPDATE:
               break;
           default:
               log.info("Invalid LibraryEvent Type!!!");
       }
    }

    private void save(LibraryEvent libraryEvent) {
        libraryEvent.getBook().setLibraryEvent(libraryEvent);
        libraryEventRepository.save(libraryEvent);
        log.info("Successfully Persisted the Library Event {} ",libraryEvent);
    }
}
