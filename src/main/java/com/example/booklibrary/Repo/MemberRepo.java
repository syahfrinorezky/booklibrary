package com.example.booklibrary.Repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booklibrary.Model.Members;

public interface MemberRepo extends JpaRepository<Members, Long> {
    List<Members> findAllByDeletedAtIsNull();
    Optional<Members> findByIdAndDeletedAtIsNull(Long id);
}
