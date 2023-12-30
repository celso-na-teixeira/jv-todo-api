package com.xteicel.todoapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Entity
public class TodoItem {

    @Id
    @GeneratedValue
    private Long id;
    private String description;
    private Boolean isCompleted;

    public TodoItem(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }


}
