package com.example.kafkalibconsumer.repository;

import com.example.kafkalibconsumer.entity.LibraryEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibraryEventRepository extends JpaRepository<LibraryEvent, Integer> {
}
