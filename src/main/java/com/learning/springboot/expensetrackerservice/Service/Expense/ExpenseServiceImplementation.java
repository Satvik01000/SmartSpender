package com.learning.springboot.expensetrackerservice.Service.Expense;

import com.learning.springboot.expensetrackerservice.Models.Category;
import com.learning.springboot.expensetrackerservice.Models.Expense;
import com.learning.springboot.expensetrackerservice.Repo.CategoryRepo;
import com.learning.springboot.expensetrackerservice.Repo.ExpenseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ExpenseServiceImplementation implements ExpenseService {
    @Autowired
    private ExpenseRepo expenseRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public Optional<List<Expense>> getAllExpenses() {
        return Optional.of(expenseRepo.findAll());
    }

    @Override
    public Optional<Page<Expense>> getAllExpensePaginatedAndSorted(int offset, int pageSize, String field) {
        return Optional.of(expenseRepo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field))));
    }

    @Override
    public Optional<Expense> getSingleExpense(UUID id) {
        return expenseRepo.findById(id);
    }

    @Override
    @Transactional
    public void addExpense(Expense expense) {
        // Check if the category already exists
        Optional<Category> existingCategory = categoryRepo.findByTitle(expense.getCategory().getTitle());
        if (existingCategory.isPresent()) {
            expense.setCategory(existingCategory.get());
        } else {
            // Merge the new category
             Category savedCategory = categoryRepo.save(expense.getCategory());
            expense.setCategory(savedCategory);
        }
        // Save the expense
        expenseRepo.save(expense);
    }
}