package com.ian.todolist.controller;

import com.ian.todolist.dto.TodoRequest;
import com.ian.todolist.model.Todo;
import com.ian.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*") // libera pro frontend
public class TodoController {

  private final TodoService service = new TodoService();

  @GetMapping
  public List<Todo> listTodos() {
    return service.listTodos();
  }

  @PostMapping
  public Todo createTodo(@RequestBody @Valid TodoRequest request) {
    return service.addTodo(request.getTitle());
  }

  @PutMapping("/{id}/done")
  public void markAsDone(@PathVariable Long id) {
    service.markAsDone(id);
  }

  @DeleteMapping("/{id}")
  public void deleteTodo(@PathVariable Long id) {
    service.deleteTodo(id);
  }
}
