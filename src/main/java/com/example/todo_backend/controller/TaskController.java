package com.example.todo_backend.controller;

import com.example.todo_backend.entity.Task;
import com.example.todo_backend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:5173", "http://localhost:5174","https://eloquent-mochi-309d45.netlify.app"})

public class TaskController {

    @Autowired
    private TaskService taskService;

    // タスク一覧取得API
    @GetMapping("/lists/{listId}/tasks")
    public ResponseEntity<?> getTasksByListId(@PathVariable("listId") Long listId) {
        try {
            List<Task> tasks = taskService.getTasksByListId(listId);
            return ResponseEntity.ok(tasks);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }

    // タスク登録API
    @PostMapping("/lists/{listId}/tasks")
    public ResponseEntity<?> createTask(@PathVariable("listId") Long listId, @RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(listId, task);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        } catch (IllegalArgumentException e) {

            if (e.getMessage().contains("対象データなし")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("message", e.getMessage()));
        }
    }

    // タスク削除API
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable("id") Long id) {
        try {
            taskService.deleteTask(id);
            return ResponseEntity.ok(Map.of("message", "削除が成功しました。"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", e.getMessage()));
        }
    }
}
