package com.example.loginopgave.models;

public class Student {

  private int id;
  private String firstName;
  private String sirName;
  private String class1;
  private int grade1;
  private String class2;
  private int grade2;
  private String class3;
  private int grade3;
  private String class4;
  private int grade4;

  public Student(int id, String firstName, String sirName, String class1, int grade1, String class2, int grade2, String class3, int grade3, String class4, int grade4) {
    this.id = id;
    this.firstName = firstName;
    this.sirName = sirName;
    this.class1 = class1;
    this.grade1 = grade1;
    this.class2 = class2;
    this.grade2 = grade2;
    this.class3 = class3;
    this.grade3 = grade3;
    this.class4 = class4;
    this.grade4 = grade4;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getSirName() {
    return sirName;
  }

  public void setSirName(String sirName) {
    this.sirName = sirName;
  }

  public String getClass1() {
    return class1;
  }

  public void setClass1(String class1) {
    this.class1 = class1;
  }

  public int getGrade1() {
    return grade1;
  }

  public void setGrade1(int grade1) {
    this.grade1 = grade1;
  }

  public String getClass2() {
    return class2;
  }

  public void setClass2(String class2) {
    this.class2 = class2;
  }

  public int getGrade2() {
    return grade2;
  }

  public void setGrade2(int grade2) {
    this.grade2 = grade2;
  }

  public String getClass3() {
    return class3;
  }

  public void setClass3(String class3) {
    this.class3 = class3;
  }

  public int getGrade3() {
    return grade3;
  }

  public void setGrade3(int grade3) {
    this.grade3 = grade3;
  }

  public String getClass4() {
    return class4;
  }

  public void setClass4(String class4) {
    this.class4 = class4;
  }

  public int getGrade4() {
    return grade4;
  }

  public void setGrade4(int grade4) {
    this.grade4 = grade4;
  }

  @Override
  public String toString() {
    return "Student{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", sirName='" + sirName + '\'' +
        ", class1='" + class1 + '\'' +
        ", grade1=" + grade1 +
        ", class2='" + class2 + '\'' +
        ", grade2=" + grade2 +
        ", class3='" + class3 + '\'' +
        ", grade3=" + grade3 +
        ", class4='" + class4 + '\'' +
        ", grade4=" + grade4 +
        '}';
  }
}
