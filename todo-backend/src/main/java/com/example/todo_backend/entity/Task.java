package com.example.todo_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long taskId;

    @Column(name = "list_id", nullable = false)
    private Long listId;

    @Column(name = "task_name", nullable = false, length = 20)
    private String taskName;

    // コンストラクタ
    public Task() {}

    public Task(Long listId, String taskName) {
        this.listId = listId;
        this.taskName = taskName;
    }

    // Getter / Setter
    public Long getTaskId() { return taskId; }
    public void setTaskId(Long taskId) { this.taskId = taskId; }
    public Long getListId() { return listId; }
    public void setListId(Long listId) { this.listId = listId; }
    public String getTaskName() { return taskName; }
    public void setTaskName(String taskName) { this.taskName = taskName; }
}
