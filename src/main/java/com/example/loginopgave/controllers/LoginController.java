package com.example.loginopgave.controllers;

import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.domain.services.LoginService;
import com.example.loginopgave.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;
import java.sql.ResultSet;


@Controller
public class LoginController {

  private LoginService loginService = new LoginService();


  @GetMapping("/")
  public String getHome() {
    return "/index";
  }


  @PostMapping("/login")
  public String loginUser(WebRequest request, Model model) {

    //Retrieve values from HTML form via WebRequest
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    // delegate work + data to login service

    User user = new User(login, password);
    boolean isExists = loginService.checkIfUserExists(user);

    // Set user in session
    if (isExists) {
      request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
      model.addAttribute("user", user);
      return "/userLogged";

      } else {

      return "redirect:/";
      /*throw new LoginSampleException("User could not be found in our user base ");*/
    }
  }


      @GetMapping("/logout")
      public String logoutUser (HttpSession session){
        session.invalidate();
        return "redirect:/";
      }


      @PostMapping("/register")
      public String createUser (WebRequest request, Model model){

        //Retrieve values from HTML form via WebRequest
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        if (login.isEmpty() || password.isEmpty()) {

          return "redirect:/";

        } else {

          // Work + data is delegated to login service
          User user = new User(login, password);
          loginService.createUser(user);

          request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
          model.addAttribute("user", user);


          // Go to page
          return "/userLogged";
        }
      }


      @GetMapping("/registerUser")
      public String registerUser () {
        return "/registerUser";
      }

     /* @PostMapping("/registerVerify")
      public String registerVerify (User user){

        if (new LoginService().checkIfUserExists(user)){
          return "userCreated";
        }
        return "registerUser";
      }*/



      @ExceptionHandler(Exception.class)
      public String exeptionHandler (Model model, Exception exception){
        model.addAttribute("message", exception.getMessage());
        return "userLogged";
      }

    }

/*@GetMapping("/userLogged")
      public String getSecretPage (WebRequest request)
        // Retrieve user from session
        User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);

        // If user object is found on session,
        // i.e. user is logged in, she/he can see secretstuff page
        if (user != null) {
          return "users/userLogged";
        } else
          return "redirect:/";
      }*/



