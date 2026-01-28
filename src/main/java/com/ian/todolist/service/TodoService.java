package com.ian.todolist.service;

import com.ian.todolist.model.Todo;
import com.ian.todolist.repository.TodoRepository;

import java.util.List;

public class TodoService {

  private final TodoRepository repository = new TodoRepository();

  public List<Todo> listTodos() {
    return repository.loadTodos();
  }

  public Todo addTodo(String title) {
    List<Todo> todos = repository.loadTodos();

    long nextId = todos.stream()
            .mapToLong(Todo::getId)
            .max()
            .orElse(0L) + 1;

    Todo todo = new Todo(nextId, title, false);

    todos.add(todo);
    repository.saveTodos(todos);

    return todo;
  }

  public void markAsDone(Long id) {
    List<Todo> todos = repository.loadTodos();

    todos.stream()
            .filter(t -> t.getId().equals(id))
            .findFirst()
            .ifPresent(t -> t.setDone(true));

    repository.saveTodos(todos);
  }

  public void deleteTodo(Long id) {
    List<Todo> todos = repository.loadTodos();

    todos.removeIf(t -> t.getId().equals(id));

    repository.saveTodos(todos);
  }
}
