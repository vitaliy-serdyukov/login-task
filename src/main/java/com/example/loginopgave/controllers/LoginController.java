package com.example.loginopgave.controllers;

import com.example.loginopgave.models.User;
import com.example.loginopgave.repository.DBManager;
import com.example.loginopgave.services.GameService;
import com.example.loginopgave.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class LoginController {

private int counter = 0;


  @GetMapping("/check")
  @ResponseBody
  public String checkConnection() {
    Connection connection = DBManager.getConnection();
    if (connection != null)
      return "Yeap - I made it to the DB!!!!!!";
    else return "Øv, øv og atter øv :-(";
  }

  @GetMapping("/")
  public String pageController(WebRequest request, Model model) {
    counter++;
    model.addAttribute("counter", counter);
    List<User> users = (ArrayList<User>) request.getAttribute("users", WebRequest.SCOPE_SESSION);
    return "index";
  }


  @GetMapping("/registerUser")
  public String registerUser(){
    return "registerUser";
  }

  @PostMapping("/registerVerify")
  public String registerVerify(User userEntered){
    UserService us = new UserService();
    try {
      if (us.checkIfExists(userEntered)){
        return "userCreated";
      }

    } catch (SQLException e) {
      e.printStackTrace();
    }
    return "registerUser";
  }

  @PostMapping("/createUser")
  public String createUser(@RequestParam String login, @RequestParam String password, Model model) {
    if (login.isEmpty() || password.isEmpty()) {
      return "redirect:/";

    } else {
      UserService service = new UserService();
      User nyUser = new User(login, password);
      try {
        if (service.checkIfExists(nyUser)){
          return "registerUser";
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      model.addAttribute("user", nyUser);
      service.saveUserToDB(nyUser);
      return "userCreated";
    }
  }


  @PostMapping("/loginUser")
  public String loginUser(@RequestParam String login, @RequestParam String password, Model model) {
      UserService service = new UserService();
      User userEntered = new User(login,password);
      model.addAttribute("user", userEntered);
    boolean isValid = false;
    try {
      isValid = service.loginUser(userEntered);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (isValid){
               return "userLogged";
      }
               return "redirect:/";
    }

    @GetMapping("/guessNumber")
    public String sendToGuessNumber(){
    return "guessNumber";
    }


  @PostMapping("/playGame")
  public String loginUser(@RequestParam int humanNumber, Model model){
    GameService gameService = new GameService();
    gameService.chooseNumberComputer();
    model.addAttribute("gameService", gameService);
         if (gameService.getPcNumber() == humanNumber){
      return "gameResultTrue";
    }
    return "gameResultFalse";
   }

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
     }*/

  @GetMapping("/showGrades")
  public String showGrades(){

    return "showGrades";
  }
}




