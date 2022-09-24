package com.example.kafkalibconsumer.entity;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookId;
    private String bookName;
    private String author;
    @OneToOne
    @JoinColumn(name = "lib_evnt_id")
    private LibraryEvent libraryEvent;
}
