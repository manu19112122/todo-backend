package com.example.todo_backend.repository;

import com.example.todo_backend.entity.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface TodoListRepository extends JpaRepository<TodoList, Long> {
    Optional<TodoList> findByListName(String listName);
}

