package com.learning.springboot.expensetrackerservice.Controller;

import com.learning.springboot.expensetrackerservice.Models.Expense;
import com.learning.springboot.expensetrackerservice.Service.Expense.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
@CrossOrigin
@RestController
@RequestMapping("/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;
    @Autowired
    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public Optional<List<Expense>> getAllExpenses() {
        return expenseService.getAllExpenses();
    }

    @GetMapping("/paginated/{offset}/{pageSize}/{field}")
    public Optional<Page<Expense>> getAllExpensesInSortedWay(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field){
        return expenseService.getAllExpensePaginatedAndSorted(offset, pageSize, field);
    }

    @GetMapping("/{id}")
    public Optional<Expense> getSingleExpense(UUID id){
        return expenseService.getSingleExpense(id);
    }

    @PostMapping
    public void addExpense(@RequestBody Expense e){
        expenseService.addExpense(e);
    }
}