package com.example.booklibrary.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booklibrary.Model.Books;

public interface BookRepo extends JpaRepository<Books, Long> {
    List<Books> findAllByDeletedAtIsNull();
    Optional<Books> findByIdAndDeletedAtIsNull(Long id);
}
