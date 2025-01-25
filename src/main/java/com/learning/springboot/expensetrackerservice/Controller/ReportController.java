package com.learning.springboot.expensetrackerservice.Controller;

import com.learning.springboot.expensetrackerservice.Service.Report.MonthlyReportService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/report")
@CrossOrigin
public class ReportController {
    private final MonthlyReportService monthlyReportService;

    public ReportController(MonthlyReportService monthlyReportService) {
        this.monthlyReportService = monthlyReportService;
    }

    @GetMapping("/credit/{user_id}")
    public Long totalCredited(@PathVariable UUID user_id){
        return monthlyReportService.totalCredited(user_id);
    }

    @GetMapping("/debit/{user_id}")
    public Long totalDebited(@PathVariable UUID user_id){
        return monthlyReportService.totalDebited(user_id);
    }

}
