package com.example.loginopgave.services;

import com.example.loginopgave.models.Student;
import com.example.loginopgave.models.User;
import com.example.loginopgave.repository.DBManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UserService {


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

  public ResultSet getAllUsers() {
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

  public boolean checkIfExists(User userEntered) throws SQLException {
    ResultSet rs = getAllUsers();
    String userTempLogin;
    while (rs.next()) {
      userTempLogin = rs.getString(1);
      if ((userTempLogin).equals(userEntered.getLogin())) {
        return true;
      }
    }
    return false;
  }


  public boolean loginUser(User userEntered) throws SQLException {
    ResultSet rs = getAllUsers();
    User temp;
    while (rs.next()) {
      temp = new User(rs.getString(1), rs.getString(2));
      if ((temp.getLogin()).equals(userEntered.getLogin()) &&
          (temp.getPassword()).equals(userEntered.getPassword())) {

        return true;
      }
    }
    return false;
  }


  public ResultSet getIfStudent(User userEntered) {
    ResultSet resSet = null;
    String SQL = "SELECT students.id, students.first_name, students.sir_name, students.class_1, students.grade_1, students.class_2, students.grade_2, students.class_3, students.grade_3, students.class_4, students.grade_4\n" +
        "  FROM users\n" +
        "  INNER JOIN students ON users.login=students.first_name\n" +
        "  WHERE users.login LIKE \"userTempLogin\"";
    ;
    try {
      PreparedStatement ps = DBManager.getConnection().prepareStatement(SQL);
      resSet = ps.executeQuery();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return resSet;
  }

  public boolean checkIfStudent(User userEntered) {
    ResultSet rs = getIfStudent(userEntered);
    try {
      if (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }

  public Student returnStudentWithGrades(User userEntered) {
    Student tmp = null;
    ResultSet resSet = getIfStudent(userEntered);
    try {
      if (resSet.next()) {
        tmp = new Student(resSet.getInt(1),
            resSet.getString(2),
            resSet.getString(3),
            resSet.getString(4),
            resSet.getInt(5),
            resSet.getString(6),
            resSet.getInt(7),
            resSet.getString(8),
            resSet.getInt(9),
            resSet.getString(10),
            resSet.getInt(11));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return tmp;

  }
}
