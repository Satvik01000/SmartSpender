package com.learning.springboot.expensetrackerservice.Service.Report;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface MonthlyReportService {
    Long totalDebited(UUID user_id);
    Long totalCredited(UUID user_id);
    List<Object[]> categoryWiseSpend(UUID user_id);
}
