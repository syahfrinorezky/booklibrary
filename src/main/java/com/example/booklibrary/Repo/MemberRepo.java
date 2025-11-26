package com.example.booklibrary.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.booklibrary.Model.Members;

public interface MemberRepo extends JpaRepository<Members, Long> {
}
