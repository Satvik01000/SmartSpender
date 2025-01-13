package com.learning.springboot.expensetrackerservice.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Setter
@NoArgsConstructor
@Getter
@AllArgsConstructor
@Builder
@Entity
public class Expense extends BaseModel {
    @Column
    @JsonProperty("amount")
    private Long amount;

    @Column
    @JsonProperty("spentWhere")
    private String spentWhere;

    @Column
    @JsonProperty("type")
    private String type;

    @Column
    @JsonProperty("Date")
    private Date date;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn
    @JsonProperty("category")
    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}