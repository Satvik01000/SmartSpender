package com.learning.springboot.expensetrackerservice.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Category extends BaseModel {
    private String title;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Expense> expenseList;

}