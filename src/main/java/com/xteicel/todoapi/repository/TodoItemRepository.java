package com.xteicel.todoapi.repository;

import com.xteicel.todoapi.model.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoItemRepository extends JpaRepository<TodoItem, Long> {
}
