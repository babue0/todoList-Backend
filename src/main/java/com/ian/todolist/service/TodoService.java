package com.ian.todolist.service;

import com.ian.todolist.model.Todo;
import com.ian.todolist.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

  private final TodoRepository repository;

  public TodoService(TodoRepository repository) {
    this.repository = repository;
  }

  public List<Todo> listTodos() {
    return repository.findAll();
  }

  public Todo addTodo(String title) {
    Todo todo = new Todo(title);
    return repository.save(todo);
  }

  public void markAsDone(Long id) {
    Todo todo = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

    todo.setDone(true);
    repository.save(todo);
  }

  public void deleteTodo(Long id) {
    repository.deleteById(id);
  }
}
