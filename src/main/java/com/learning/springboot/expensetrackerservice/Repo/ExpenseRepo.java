package com.learning.springboot.expensetrackerservice.Repo;

import com.learning.springboot.expensetrackerservice.Models.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, UUID> {
}
