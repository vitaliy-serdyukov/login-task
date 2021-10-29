package com.example.loginopgave.controllers;

import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.repositories.DBManager;
import com.example.loginopgave.domain.services.UserService;
import com.example.loginopgave.repositories.DataMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
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
  public String registerUser() {
    return "registerUser";
  }

  @PostMapping("/registerVerify")
  public String registerVerify(User userEntered) {
    UserService us = new UserService();
    DataMapper dm = new DataMapper();
    try {
      if (dm.checkIfExistsInDB(userEntered)) {
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
      DataMapper dataMapper = new DataMapper();
      UserService service = new UserService();
      User nyUser = new User(login, password);
      try {
        if (dataMapper.checkIfExistsInDB(nyUser)) {
          return "registerUser";
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
      model.addAttribute("user", nyUser);
      dataMapper.saveUserToDB(nyUser);
      return "userCreated";
    }
  }


  @PostMapping("/loginUser")// virker den???
  public String loginUser(@RequestParam String login, @RequestParam String password, Model model,
                          WebRequest request, HttpSession session){
    UserService service = new UserService();
    User user = new User(login,password);
    DataMapper dm = new DataMapper();
    model.addAttribute("user", user);
    boolean isValid = false;
    try {
       isValid = service.loginUser(user); //????
     /* isValid = service.loginUser(user);*/
    } catch (SQLException e) {
      e.printStackTrace();
    }
    if (isValid){
        if(session.getAttribute("user")==null){
    request.setAttribute("user", user, WebRequest.SCOPE_SESSION); // flyttes til HTML Controller?
    return"userLogged";
        }
      }
    return "redirect:/";
    }



 /* @ExceptionHandler(Exception.class)
  public String exeptionHandler(Model model, Exception exception){
   model.addAttribute("message", exception.getMessage());
     return "userLogged";
    }*/

  }





