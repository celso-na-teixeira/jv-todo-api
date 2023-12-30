package com.xteicel.todoapi.repository;

import com.xteicel.todoapi.model.TodoItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    private final Logger logger = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(TodoItemRepository repository) {
        return args -> {
            logger.info("Preloading " + repository.save(new TodoItem("Create todo-api app", false)));
            logger.info("Preloading " + repository.save(new TodoItem("Create unit test for todo-api app", false)));
            logger.info("Preloading " + repository.save(new TodoItem("Create integration tests for todo-api app", false)));
            logger.info("Preloading " + repository.save(new TodoItem("Create create front-end app for", false)));
            logger.info("Preloading " + repository.save(new TodoItem("Connect front-end with todo-api app", false)));
        };
    }
}
