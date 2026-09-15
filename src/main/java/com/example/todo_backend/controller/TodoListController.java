package com.example.todo_backend.controller;

import com.example.todo_backend.entity.TodoList;
import com.example.todo_backend.service.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lists")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174","https://eloquent-mochi-309d45.netlify.app"})

public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    // リスト一覧取得API
    @GetMapping
    public ResponseEntity<List<TodoList>> getAllLists() {
        try {
            List<TodoList> lists = todoListService.getAllLists();
            return ResponseEntity.ok(lists);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // リスト登録API
    @PostMapping
    public ResponseEntity<?> createList(@RequestBody TodoList todoList) {
        try {
            TodoList createdList = todoListService.createList(todoList);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdList);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    // リスト削除API
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteList(@PathVariable("id") Long id) {
        try {
            todoListService.deleteList(id);
            return ResponseEntity.ok(Map.of("message", "削除が成功しました。"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }
}
