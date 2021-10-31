package com.example.loginopgave.domain.models;

public class User {

  private int id;
  private String login;
  private String password;

  public User(String login, String password) {
    this.login = login;
    this.password = password;
  }

  public User (){
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "User{" +
        "login='" + login + '\'' +
        ", password='" + password + '\'' +
        '}';
  }
}
