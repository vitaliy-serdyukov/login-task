package com.example.loginopgave.domain.services;

import java.util.Random;

public class GameService {

  private int pcNumber = chooseNumberComputer();

  public int getPcNumber() {
    return pcNumber;
  }

  public void setPcNumber(int pcNumber) {
    this.pcNumber = pcNumber;
  }

  public int chooseNumberComputer(){
    Random random = new Random();
    int computerNumber = random.nextInt(10) + 1;
   return computerNumber;

  }


}
