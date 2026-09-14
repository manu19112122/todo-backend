package com.example.todo_backend.service;

import com.example.todo_backend.entity.Task;
import com.example.todo_backend.repository.TaskRepository;
import com.example.todo_backend.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TodoListRepository todoListRepository;

    public List<Task> getTasksByListId(Long listId) {
        if (!todoListRepository.existsById(listId)) {
            throw new IllegalArgumentException("対象データなし：指定されたリストが存在しません。");
        }
        return taskRepository.findByListId(listId);
    }

    public Task createTask(Long listId, Task task) {
        if (!todoListRepository.existsById(listId)) {
            throw new IllegalArgumentException("対象データなし：指定されたリストが存在しません。");
        }
        if (task.getTaskName() == null || task.getTaskName().trim().isEmpty() || task.getTaskName().length() > 20) {
            throw new IllegalArgumentException("入力不備：タスク名は1文字以上20文字以内で入力してください。");
        }
        task.setListId(listId);
        return taskRepository.save(task);
    }

    public void deleteTask(Long taskId) {
        if (!taskRepository.existsById(taskId)) {
            throw new IllegalArgumentException("対象データなし：削除するタスクが存在しません。");
        }
        taskRepository.deleteById(taskId);
    }
}
