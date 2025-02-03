package com.learning.springboot.expensetrackerservice.Service.Report;

import java.util.List;
import java.util.UUID;

public interface ReportService {
    Long totalDebited(UUID userId);
    Long totalCredited(UUID user_id);
    List<Object[]> categoryWiseSpend(UUID user_id);

    Long currentBalance(UUID userId);

    Long mostExpensivePurchase(UUID userId);
}
