package com.xteicel.todoapi.service;

import com.xteicel.todoapi.dto.TodoItemDTO;
import com.xteicel.todoapi.exceptions.TodoItemException;
import com.xteicel.todoapi.model.TodoItem;
import com.xteicel.todoapi.repository.TodoItemRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class TodoServiceImpl implements TodoService {

  private final Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
  private final ModelMapper modelMapper;
  @Autowired
  private TodoItemRepository repository;

  public TodoServiceImpl() {
    modelMapper = new ModelMapper();
  }

  public TodoItemDTO convertToDTO(TodoItem todoItem) {
    if (todoItem == null) {
      String errorMessage = "TodoItem is null. Unable to convert to TodoItemDTO.";
      logger.error(errorMessage);
      throw new NullPointerException(errorMessage);
    }

    try {
      return modelMapper.map(todoItem, TodoItemDTO.class);
    } catch (Exception e) {
      String errorMessage = String.format(
          "Error converting TodoItem to TodoItemDTO. TodoItemId: %s", todoItem.getId());
      logger.error(errorMessage, e);
      throw new RuntimeException(errorMessage, e);
    }
  }

  @Override
  public List<TodoItemDTO> getAll() {
    try {
      return repository.findAll().stream().map(this::convertToDTO).toList();
    } catch (DataAccessException e) {
      logger.error("Error: failed to retrieve todo items from the database", e);

      throw new TodoItemException("Failed to retrieve todo items", e);
    } catch (Exception e) {
      logger.error("Unexpected error while retrieving todo items", e);

      throw new TodoItemException("Failed to get todo items", e);
    }
  }
}
