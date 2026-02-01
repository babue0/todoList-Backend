package com.ian.todolist.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "users")
public class User {

  @Id
  private UUID id;

  protected User(){}

  public User(UUID id) {
    this.id = id;
  }

  public UUID getId() {
    return id;
  }
}
