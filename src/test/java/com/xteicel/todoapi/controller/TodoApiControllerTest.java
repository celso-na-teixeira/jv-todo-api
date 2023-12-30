package com.xteicel.todoapi.controller;

import static org.mockito.Mockito.when;

import com.xteicel.todoapi.dto.TodoItemDTO;
import com.xteicel.todoapi.exceptions.TodoItemException;
import com.xteicel.todoapi.service.TodoService;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class TodoApiControllerTest {

  @InjectMocks
  private TodoApiController controller;
  @Mock
  private TodoService service;

  @Test
  public void getItems_ReturnListOfTodoItemsDTOs() {
    List<TodoItemDTO> mockTodoItems = Arrays.asList(
        new TodoItemDTO("Task 1", false),
        new TodoItemDTO("Task 2", true)
    );

    when(service.getAll()).thenReturn(mockTodoItems);

    ResponseEntity<List<TodoItemDTO>> response = controller.getItems();

    Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
    Assertions.assertEquals(mockTodoItems, response.getBody());
  }

  @Test
  public void getItems_shouldThrowTodoItemException() {

    when(service.getAll()).thenThrow(new TodoItemException("Mocking a exception for getAll"));

    ResponseEntity<List<TodoItemDTO>> response = controller.getItems();

    Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());

  }

}
