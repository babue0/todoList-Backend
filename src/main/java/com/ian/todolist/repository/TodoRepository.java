package com.ian.todolist.repository;

import com.ian.todolist.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TodoRepository  extends JpaRepository<Todo, Long>{

  List<Todo> findByUserId(UUID userId);

  Optional<Todo> findByIdAndUserId(Long id, UUID userId);
}
