package com.learning.springboot.expensetrackerservice.Service.Expense;

import com.learning.springboot.expensetrackerservice.Models.Expense;
import org.springframework.data.domain.Page;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseService {
    Optional<List<Expense>> getAllExpenses();
    Optional<Page<Expense>> getAllExpensePaginatedAndSorted(int offset, int pageSize, String field);
    Optional<Expense> getSingleExpense(UUID id);
    void addExpense(Expense expense);
}