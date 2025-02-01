package com.learning.springboot.expensetrackerservice.Repo;

import com.learning.springboot.expensetrackerservice.Models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepo extends JpaRepository<Category, UUID> {
    Optional<Category> findByTitle(String title);
    void deleteByTitle(String title);
}