package com.example.kafkalibproducer.domain;

import com.example.kafkalibproducer.enums.LibraryEventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LibraryEvent {

    private Integer id;
    private LibraryEventType libraryEventType;
    private Book book;
}
