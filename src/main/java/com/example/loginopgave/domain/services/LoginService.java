package com.example.loginopgave.domain.services;

import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;


public class LoginService {

  private UserRepository ur = new UserRepository();


  public boolean checkIfUserExists(User userEntered) {
    ResultSet rs = ur.getAllUsersFromDB();
    String userTempLogin;
    try {
      while (rs.next()) {
        userTempLogin = rs.getString(1);
        if ((userTempLogin).equals(userEntered.getLogin())) {
          return true;
        }
      }
    } catch (SQLException e){
      e.printStackTrace();
    }
    return false;
  }

  public void createUser(User user) {
       ur.saveUserToDB(user);
  }

}
