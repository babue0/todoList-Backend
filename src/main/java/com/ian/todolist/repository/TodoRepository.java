package com.ian.todolist.repository;

import com.ian.todolist.model.Todo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TodoRepository {

  private final File file = new File("todos.json");
  private final ObjectMapper mapper = new ObjectMapper();

  public List<Todo> loadTodos(){
    try{
      if (!file.exists()){
        return new ArrayList<>();
      }
      return mapper.readValue(file, new TypeReference<List<Todo>>() {});
    } catch (IOException e) {
      throw new RuntimeException("Erro ao ler arquivo", e);
    }
  }

  public void saveTodos(List<Todo> todos) {
    try{
      mapper.writerWithDefaultPrettyPrinter().writeValue(file, todos);
    } catch (IOException e){
      throw new RuntimeException("Erro ao gravar arquivo", e);
    }
  }
}
