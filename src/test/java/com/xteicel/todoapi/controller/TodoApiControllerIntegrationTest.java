package com.xteicel.todoapi.controller;

import com.xteicel.todoapi.service.TodoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class TodoApiControllerIntegrationTest {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private TodoService service;

  @Test
  public void getItems_shouldReturnItems_andStatus200() throws Exception {
    mvc.perform(get("/todo-api/items"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$[0].description").value("Create todo-api app"));
  }
}
