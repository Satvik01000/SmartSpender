package com.learning.springboot.expensetrackerservice.Repo;

import com.learning.springboot.expensetrackerservice.Models.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, UUID> {
    List<Expense> findByUserId(UUID userId);

    @Query("select e from Expense e where e.user.id = :userId")
    Page<Expense> findAllByUserId(UUID userId, PageRequest pageRequest);

    @Query(
        "SELECT SUM(e.amount) " +
        "FROM Expense e " +
        "WHERE e.user.id = :userId AND e.type = 'credited' " +
        "AND FUNCTION('MONTH', e.date) = FUNCTION('MONTH', CURRENT_DATE) " +
        "AND FUNCTION('YEAR', e.date) = FUNCTION('YEAR', CURRENT_DATE)"
    )
    Long totalCredited(UUID userId);


    @Query(
        "SELECT SUM(e.amount) " +
        "FROM Expense e " +
        "WHERE e.user.id = :userId AND e.type = 'debited' " +
        "AND FUNCTION('MONTH', e.date) = FUNCTION('MONTH', CURRENT_DATE) " +
        "AND FUNCTION('YEAR', e.date) = FUNCTION('YEAR', CURRENT_DATE)"
    )
    Long totalDebited(UUID userId);

    @Query(
        "SELECT e.category.title AS category, SUM(e.amount) AS total_spent " +
        "FROM Expense e " +
        "WHERE e.user.id = :userId " +
        "AND MONTH(e.date) = MONTH(CURRENT_DATE) " +
        "AND YEAR(e.date) = YEAR(CURRENT_DATE) " +
        "GROUP BY e.category.title"
    )
    List<Object[]> totalSpentByCategory(UUID userId);

    @Query(
        "SELECT e "+
        "FROM Expense e "+
        "WHERE e.user.id=:userId AND e.type='debited' "+
        "ORDER BY e.amount DESC "+
        "LIMIT 1"
    )
    Expense mostExpensivePurchase(UUID userId);

    @Query(
        "SELECT SUM(e.amount) AS total_amount, e.date " +
        "FROM Expense e " +
        "WHERE e.user.id = :userId " +
        "AND FUNCTION('YEAR', e.date) = FUNCTION('YEAR', CURRENT_DATE) " +
        "AND FUNCTION('MONTH', e.date) = FUNCTION('MONTH', CURRENT_DATE) " +
        "AND e.date <= CURRENT_DATE " +
        "GROUP BY e.date " +
        "ORDER BY e.date"
    )
    List<Object[]> dailySpendingInAMonth(UUID userId);

}