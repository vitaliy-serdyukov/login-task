package com.example.loginopgave.repositories;


import com.example.loginopgave.domain.models.User;

import java.sql.*;

public class UserRepository {

  public ResultSet getAllUsersFromDB() {
    ResultSet resSet = null;
    String select = "SELECT login, password FROM users";
    try {
      PreparedStatement ps = DBManager.getConnection().prepareStatement(select);
      resSet = ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resSet;
  }


  public void saveUserToDB(User user){
    try {
      Connection con = DBManager.getConnection();
      String SQL = "INSERT INTO users (login, password) VALUES (?,?)";
      PreparedStatement ps = con.prepareStatement(SQL/*, Statement.RETURN_GENERATED_KEYS*/);
      ps.setString(1, user.getLogin());
      ps.setString(2, user.getPassword());
      ps.executeUpdate();
      /*ResultSet ids = ps.getGeneratedKeys();
      ids.next();
      int id = ids.getInt(1);
      user.setId(id);
      return user;*/
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
  }
}
