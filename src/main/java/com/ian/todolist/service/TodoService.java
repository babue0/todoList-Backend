package com.ian.todolist.service;

import com.ian.todolist.model.Todo;
import com.ian.todolist.model.User;
import com.ian.todolist.repository.TodoRepository;
import com.ian.todolist.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoService {

  private final TodoRepository todoRepository;
  private final UserRepository userRepository;

  public TodoService(TodoRepository todoRepository, UserRepository userRepository) {
    this.todoRepository = todoRepository;
    this.userRepository = userRepository;
  }

  private User getOrCreateUser(UUID userId) {
    return userRepository.findById(userId)
            .orElseGet(() -> userRepository.save(new User(userId)));
  }

  public List<Todo> list(UUID userId) {
    return todoRepository.findByUserId(userId);
  }

  public Todo create(UUID userId, String title) {
    User user = getOrCreateUser(userId);
    Todo todo = new Todo(title, user);
    return todoRepository.save(todo);
  }

  public void toggleDone(UUID userId, Long todoId, boolean done) {
    Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
            .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

    todo.setDone(done);
    todoRepository.save(todo);
  }

  public void delete(UUID userId, Long todoId) {
    Todo todo = todoRepository.findByIdAndUserId(todoId, userId)
            .orElseThrow(() -> new RuntimeException("Todo não encontrado"));

    todoRepository.delete(todo);
  }
}
