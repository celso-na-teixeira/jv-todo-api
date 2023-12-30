package com.xteicel.todoapi.controller;

import com.xteicel.todoapi.dto.TodoItemDTO;
import com.xteicel.todoapi.exceptions.TodoItemException;
import com.xteicel.todoapi.service.TodoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todo-api")
public class TodoApiController {

  private final Logger logger = LoggerFactory.getLogger(TodoApiController.class);
  @Autowired
  private TodoService service;

  @GetMapping("/items")
  public ResponseEntity<List<TodoItemDTO>> getItems() {
    try {
      return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    } catch (TodoItemException e) {
      logger.error("Failed to retrieve todo items", e);
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
