package com.example.msdocsspringboot.controller;



import com.example.msdocsspringboot.entity.TodoItem;
import com.example.msdocsspringboot.service.ITodoItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author laker
 * @since 2021-08-11
 */
@RestController
public class SysTodoItemController {

    private static Logger logger = LoggerFactory.getLogger(SysTodoItemController.class);

    @Autowired
    private ITodoItemService todoItemService;

    public SysTodoItemController() {
    }

    /**
     * HTTP GET
     */
    @GetMapping(path = "/api/todolist/{index}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public TodoItem getTodoItem(@PathVariable("index") String index) {
        logger.info("GET request access '/api/todolist/{}' path.", index);
        TodoItem item = todoItemService.getById(index);
        return item;
    }

    /**
     * HTTP GET ALL
     */
    @GetMapping("/api/todolist")
//    @GetMapping(path = "/api/todolist", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<TodoItem> getAllTodoItems() {
        logger.info("GET request access '/api/todolist' path.");

        List<TodoItem> todoItems = todoItemService.list();
        return todoItems;
    }

    /**
     * HTTP POST NEW ONE
     */
    @PostMapping(path = "/api/todolist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String addNewTodoItem(@RequestBody TodoItem item) {
        logger.info("POST request access '/api/todolist' path with item: {}", item);
        try {
            item.setId(UUID.randomUUID().toString());
            todoItemService.save(item);
            return "Todo item created";
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Todo item creation failed");
        }
    }

    /**
     * HTTP PUT UPDATE
     */
    @PutMapping(path = "/api/todolist", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String updateTodoItem(@RequestBody TodoItem item) {
        logger.info("PUT request access '/api/todolist' path with item {}", item);
        try {
            todoItemService.removeById(item.getId());
            todoItemService.save(item);
            return "Todo item updated";
        } catch (Exception e) {
            logger.error("Update errors: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo item not found");
        }
    }

    /**
     * HTTP DELETE
     */
    @DeleteMapping("/api/todolist/{id}")
    public String deleteTodoItem(@PathVariable("id") String id) {
        logger.info("DELETE request access '/api/todolist/{}' path.", id);
        try {
            todoItemService.removeById(id);
            return "Todo item deleted";
        } catch (Exception e) {
            logger.error("Delete errors: ", e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todo item not found");
        }

    }

}
