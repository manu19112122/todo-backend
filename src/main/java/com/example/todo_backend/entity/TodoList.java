package com.example.todo_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "lists")
public class TodoList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "list_id")
    private Long listId;

    @Column(name = "list_name", nullable = false, unique = true, length = 20)
    private String listName;

    // コンストラクタ
    public TodoList() {}

    public TodoList(String listName) {
        this.listName = listName;
    }

    // Getter / Setter
    public Long getListId() { return listId; }
    public void setListId(Long listId) { this.listId = listId; }
    public String getListName() { return listName; }
    public void setListName(String listName) { this.listName = listName; }
}
