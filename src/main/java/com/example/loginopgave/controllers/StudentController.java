/*
package com.example.loginopgave.controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class StudentController {

   */
/*@PostMapping("/showGrades")
     public String showGrades(@RequestParam String login, @RequestParam String password){
       UserService service = new UserService();
       User userEntered = new User(login,password);
       service.getIfStudent(userEntered);
       boolean isStudent = service.checkIfStudent(userEntered);
       if(isStudent){
         return "userStudent";
       }
       return "userNotStudent";
     }*//*


  @GetMapping("/showGrades")
  public String showGrades(){

    return "showGrades";
  }
}
*/
