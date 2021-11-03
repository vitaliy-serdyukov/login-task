package com.example.loginopgave.repositories;

public class StudentRepository {

   /* public ResultSet getIfStudent(User userEntered) {
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
  }*/

  /*public boolean checkIfStudent(User userEntered) {
    ResultSet rs = getIfStudent(userEntered);
    try {
      if (rs.next()) {
        return true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return false;
  }*/

  /*public Student returnStudentWithGrades(User userEntered) {
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

  }*/
}
