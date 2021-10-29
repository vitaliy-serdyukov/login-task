package com.example.loginopgave.controllers;

import com.example.loginopgave.domain.services.GameService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class GameController {

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
}
