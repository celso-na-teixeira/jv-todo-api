package com.xteicel.todoapi.service;

import com.xteicel.todoapi.dto.TodoItemDTO;

import java.util.List;

public interface TodoService {

    List<TodoItemDTO> getAll();

}
