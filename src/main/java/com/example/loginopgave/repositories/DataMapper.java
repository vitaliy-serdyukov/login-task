package com.example.loginopgave.repositories;

import com.example.loginopgave.domain.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DataMapper {

  public void saveUserToDB(User user) {
    try {
      Connection con = DBManager.getConnection();
      String SQL = "INSERT INTO users (login, password) VALUES (?,?)";
      PreparedStatement ps = con.prepareStatement(SQL);
      ps.setString(1, user.getLogin());
      ps.setString(2, user.getPassword());
      ps.executeUpdate();
      /* ResultSet rs = ps.executeQuery();*/
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

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

  public boolean checkIfExistsInDB(User userEntered) throws SQLException {
    ResultSet rs = getAllUsersFromDB();
    String userTempLogin;
    while (rs.next()) {
      userTempLogin = rs.getString(1);
      if ((userTempLogin).equals(userEntered.getLogin())) {
        return true;
      }
    }
    return false;
  }

}
