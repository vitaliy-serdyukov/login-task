package com.example.loginopgave.controllers;

import com.example.loginopgave.domain.LoginSampleException;
import com.example.loginopgave.domain.models.User;
import com.example.loginopgave.domain.services.LoginService;
import com.example.loginopgave.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Objects;


@Controller
public class LoginController {

  private LoginService loginService = new LoginService();


  @GetMapping("/")
  public String getHome() {
    return "index";
  }


  @PostMapping("/login")
  public String loginUser(WebRequest request, Model model) throws LoginSampleException {

    //Retrieve values from HTML form via WebRequest
    String login = request.getParameter("login");
    String password = request.getParameter("password");

    // delegate work + data to login service

    User user = new User(login, password);
    boolean isExists = loginService.checkIfUserExistsLogin(user);

    // Set user in session
    if (isExists) {
      request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
      model.addAttribute("user", user);
      ArrayList<User> users = loginService.getAllUsersToArraylist();
      model.addAttribute("users", users);
      /*users.clear();*/
      return "userLogged";

      } else {
      throw new LoginSampleException("User with such login and password could not be found in our user base");
    }
  }


      @GetMapping("/logout")
      public String logoutUser (HttpSession session){
        session.invalidate();
        return "redirect:/";
      }


      @PostMapping("/register")
      public String createUser (WebRequest request, Model model) throws LoginSampleException {
        //Retrieve values from HTML form via WebRequest
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        User user = new User(login, password);


          if (Objects.equals(login, "") || Objects.equals(password, "")) {
            throw new LoginSampleException("You have to fill in all the fields...");

          } else if (new LoginService().checkIfUserExistsRegister(user)) {
            throw new LoginSampleException("The user with the such name already exists...");

          } else {

          // Work + data is delegated to login service
          loginService.createUser(user);
          request.setAttribute("user", user, WebRequest.SCOPE_SESSION);
          model.addAttribute("user", user);

          // Go to page
          return "userCreated";
          }

      }


      @GetMapping("/registerUser")
      public String registerUser () {
        return "/registerUser";
      }


      @GetMapping("/deleteUser/{login}")
      public String deleteUser (@PathVariable (value = "login") String login){
      loginService.deleteUser(login);
      return "redirect:/userLogged";
      }

      @GetMapping("/userLogged")
      public String userLogged (WebRequest request){
      User user = (User) request.getAttribute("user", WebRequest.SCOPE_SESSION);
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        return "userLogged";
      }



      @ExceptionHandler(LoginSampleException.class)
      public String handleError(Model model, Exception exception) {
      model.addAttribute("message",exception.getMessage());
      return "exceptionPage";

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



