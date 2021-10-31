package com.example.loginopgave.controllers;

import com.example.loginopgave.domain.LoginSampleException;
import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.domain.services.LoginService;
import com.example.loginopgave.repositories.UserRepository;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;


@Controller
public class LoginController {

  private LoginService loginService = new LoginService(new UserRepository());


  @GetMapping("/")
  public String getHome() {
    return "/index";
  }


  @PostMapping("/login")
  public String loginUser(WebRequest request, Model model) throws LoginSampleException {

    //Retrieve values from HTML form via WebRequest
    String login = request.getParameter("login");
    String password = request.getParameter("password");
    // delegate work + data to login service
    try {
      User userEntered = new User(login,password);
      boolean isExists = loginService.checkIfUserExists(userEntered);
    // Set user in session
    if (isExists) {
      model.addAttribute("user", userEntered);
      request.setAttribute("user", userEntered, WebRequest.SCOPE_SESSION);

      return "users/userLogged";

    } else {
      return "redirect:/";
     }
    } catch (SQLException ex) {
    throw new LoginSampleException("Could not validate user");
    }
  }

  @PostMapping("/logout")// doesn't work yet
  public String logoutUser(SessionStatus session) {
    if (session != null)
      System.out.println(session);
    return "/index";

  }



  @PostMapping("/register")
  public String createUser(WebRequest request, Model model) throws LoginSampleException {
    //Retrieve values from HTML form via WebRequest
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    if (login.isEmpty() || password.isEmpty()) {
      return "redirect:/";

    } else {
      // Work + data is delegated to login service

      User user = loginService.createUser(login, password);
      model.addAttribute("user", user);
      request.setAttribute("user", user, WebRequest.SCOPE_SESSION);

      // Go to page dependent on role
      return "users/userLogged";
    }
  }


  @GetMapping("/registerUser")
  public String registerUser() {
    return "users/registerUser";
  }

  @PostMapping("/registerVerify")
  public String registerVerify(User userEntered) {

    UserRepository ur = new UserRepository();
    try {
      if (ur.saveUserToDB(userEntered) != null) {
        return "users/userCreated";
      }

    } catch (LoginSampleException e) {
      e.printStackTrace();
    }
    return "users/registerUser";
  }


  @GetMapping("/userLogged")
  public String getSecretPage(WebRequest request) {
    // Retrieve user from session
    User user = (User) request.getAttribute("user",WebRequest.SCOPE_SESSION);

    // If user object is found on session,
    // i.e. user is logged in, she/he can see secretstuff page
    if (user != null) {
      return "users/userLogged";
    }
    else
      return "redirect:/";
  }

  @ExceptionHandler(Exception.class)
  public String exeptionHandler(Model model, Exception exception){
   model.addAttribute("message", exception.getMessage());
     return "userLogged";
   }

}





