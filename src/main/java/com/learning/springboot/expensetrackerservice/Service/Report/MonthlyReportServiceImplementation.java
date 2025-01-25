package com.learning.springboot.expensetrackerservice.Service.Report;

import com.learning.springboot.expensetrackerservice.Repo.ExpenseRepo;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MonthlyReportServiceImplementation implements MonthlyReportService{
    private final ExpenseRepo expenseRepo;

    public MonthlyReportServiceImplementation(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    @Override
    public Long totalDebited(UUID user_id) {
        return expenseRepo.totalDebited(user_id);
    }

    @Override
    public Long totalCredited(UUID user_id) {
        return expenseRepo.totalCredited(user_id)!=null ? expenseRepo.totalCredited(user_id) : 0L;
    }

}
