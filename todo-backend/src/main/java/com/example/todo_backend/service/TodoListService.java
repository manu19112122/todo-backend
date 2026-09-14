package com.example.todo_backend.service;

import com.example.todo_backend.entity.TodoList;
import com.example.todo_backend.repository.TodoListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    public List<TodoList> getAllLists() {
        return todoListRepository.findAll();
    }

    public TodoList createList(TodoList todoList) {
        if (todoList.getListName() == null || todoList.getListName().trim().isEmpty() || todoList.getListName().length() > 20) {
            throw new IllegalArgumentException("入力不備：リスト名は1文字以上20文字以内で入力してください。");
        }
        if (todoListRepository.findByListName(todoList.getListName()).isPresent()) {
            throw new IllegalStateException("既に登録されているリスト名は使用できません。");
        }
        return todoListRepository.save(todoList);
    }

    public void deleteList(Long listId) {
        if (!todoListRepository.existsById(listId)) {
            throw new IllegalArgumentException("対象データなし：削除するリストが存在しません。");
        }
        todoListRepository.deleteById(listId);
    }
}
