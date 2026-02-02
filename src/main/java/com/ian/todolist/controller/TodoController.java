package com.ian.todolist.controller;

import com.ian.todolist.dto.TodoRequest;
import com.ian.todolist.model.Todo;
import com.ian.todolist.service.TodoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
@CrossOrigin(origins = "*") // libera pro frontend
public class TodoController {

  private final TodoService service;

  public TodoController(TodoService service){
    this.service = service;
  }

  @GetMapping
  public List<Todo> list(
    @RequestHeader("X-USER-ID") UUID userId){
    return service.list(userId);
  }

  @PostMapping
  public Todo createTodo(@RequestHeader("X-USER-ID") UUID userId, @Valid @RequestBody TodoRequest request) {
    return service.create(userId, request.getTitle());
  }

  @PutMapping("/{id}/done")
  public void toggleDone(
          @RequestHeader("X-USER-ID") UUID userId,
          @PathVariable Long id,
          @RequestParam boolean done) {

    service.toggleDone(userId, id, done);
  }

  @DeleteMapping("/{id}")
  public void delete(
          @RequestHeader("X-USER-ID") UUID userId,
          @PathVariable Long id) {

    service.delete(userId, id);
  }
}
