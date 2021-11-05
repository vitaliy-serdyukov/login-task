package com.example.loginopgave.domain.services;

import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.repositories.UserRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class LoginService {

  private UserRepository ur = new UserRepository();


  public ArrayList<User> getAllUsersToArraylist() {
    ResultSet rs = ur.getAllUsersFromDB();
    ArrayList<User> users = new ArrayList<>();
    try {
      while (rs.next()) {
        User user = new User(rs.getString(1), rs.getString(2));
        users.add(user);
       }
    } catch(SQLException e){
    e.printStackTrace();
    }
    return users;

  }

  /*public void deleteUser(User user) {
    ResultSet rs = ur.getAllUsersFromDB();
    try {
      while (rs.next()) {
        String login = rs.getString(1);
        if (login.equals(user.getLogin())){

        }
      }
    } catch(SQLException e){
      e.printStackTrace();
    }


  }*/


  public boolean checkIfUserExistsLogin(User userEntered) {
    ResultSet rs = ur.getAllUsersFromDB();
    try {
      while (rs.next()) {
      String userTempLogin = rs.getString(1);
      String userTempPassword = rs.getString(2);
        if ((userTempLogin).equals(userEntered.getLogin()) &&
            (userTempPassword).equals(userEntered.getPassword())) {
          return true;
        }
      }
    } catch (SQLException e){
      e.printStackTrace();
    }
    return false;
  }

  public boolean checkIfUserExistsRegister(User userEntered) {
    ResultSet rs = ur.getAllUsersFromDB();
    try {
      while (rs.next()) {
        String userTempLogin = rs.getString(1);
        String userTempPassword = rs.getString(2);
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

  public void deleteUser(String login){
     ur.deleteUserFromDB(login);
  }

}
