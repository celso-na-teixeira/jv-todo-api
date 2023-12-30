package com.xteicel.todoapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TodoItemDTO {
    private Long id;
    private String description;
    private Boolean isCompleted;

    public TodoItemDTO(String description, Boolean isCompleted) {
        this.description = description;
        this.isCompleted = isCompleted;
    }
}


