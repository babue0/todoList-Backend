package com.ian.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TodoRequest {

  @NotBlank(message = "O título não pode ser vazio")
  @Size(max=255, message = "O titulo nao pode ter mais do que 255 caracteres")
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


}
