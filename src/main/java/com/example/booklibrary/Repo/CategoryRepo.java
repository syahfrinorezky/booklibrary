package com.example.booklibrary.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.booklibrary.Model.Categories;

public interface CategoryRepo extends JpaRepository<Categories, Long> {
    List<Categories> findAllByDeletedAtIsNull();
    Optional<Categories> findByIdAndDeletedAtIsNull(Long id);
}
