package com.learning.springboot.expensetrackerservice.Service.Category;

import com.learning.springboot.expensetrackerservice.Models.Category;
import com.learning.springboot.expensetrackerservice.Models.Expense;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    void addCategory(Category category);
    void deleteCategory(String title);
    Optional<List<Category>> getAllCategories();
    Optional<Page<Category>> getAllCategoryPaginatedAndSorted(int offset, int pageSize, String field);
    Optional<Category> getCategoryByTitle(String title);
}
