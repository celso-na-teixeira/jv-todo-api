package com.xteicel.todoapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.xteicel.todoapi.dto.TodoItemDTO;
import com.xteicel.todoapi.exceptions.TodoItemException;
import com.xteicel.todoapi.model.TodoItem;
import com.xteicel.todoapi.repository.TodoItemRepository;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;

@ExtendWith(MockitoExtension.class)
public class TodoServiceImplTest {

  @Mock
  private TodoItemRepository repository;

  @InjectMocks
  private TodoServiceImpl todoService;

  @After
  public void tearDown() {
    Mockito.reset(repository);
  }

  @DisplayName("getAll Should return a list of TodoItemsDTO")
  @Test
  public void getAll_shouldReturnListOfTodoItemsDTOs() {
    List<TodoItem> todoItems = Arrays.asList(new TodoItem("list all items", false),
        new TodoItem("delete items", false));
    List<TodoItemDTO> expectedTodoItemsDTO = Arrays.asList(new TodoItemDTO("list all items", false),
        new TodoItemDTO("delete items", false));

    when(repository.findAll()).thenReturn(todoItems);

    List<TodoItemDTO> result = todoService.getAll();

    assertEquals(expectedTodoItemsDTO, result);

    verify(repository, times(1)).findAll();
  }

  @DisplayName("getAll Should throw TodoItemException")
  @Test
  public void getAll_shouldThrowTodoItemException() {

    when(repository.findAll()).thenThrow(new RuntimeException("Mocking a exception for getAll"));

    assertThrows(TodoItemException.class, () -> todoService.getAll());

  }

  @DisplayName("getAll Should throw DataAccessException")
  @Test
  public void getAll_shouldThrowDataAccessException() {

    when(repository.findAll()).thenThrow(
        new DataAccessException("Mocking a database connection exception for getAll") {
        });

    assertThrows(TodoItemException.class, () -> todoService.getAll());

  }

  @DisplayName("Should convert TodoItem to TodoItemDTO")
  @Test
  public void convertToDTO_shouldConvertTodoItemToTodoItemDTO() {
    TodoItem item = new TodoItem("list all items", false);

    TodoItemDTO expectedItem = new TodoItemDTO("list all items", false);

    assertEquals(expectedItem, todoService.convertToDTO(item));
  }

  @DisplayName("convertToDTO - Should throw Exception")
  @Test
  public void convertToDTO_shouldThrowException() {
    assertThrows(NullPointerException.class, () -> todoService.convertToDTO(null));
  }
}
