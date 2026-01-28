package com.ian.todolist.dto;

import jakarta.validation.constraints.NotBlank;

public class TodoRequest {

  @NotBlank(message = "O título não pode ser vazio")
  private String title;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }


}
