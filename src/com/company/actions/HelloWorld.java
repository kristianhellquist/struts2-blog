package com.company.actions;

import com.opensymphony.xwork2.ActionSupport; 

public class HelloWorld extends ActionSupport {
  private String message;

  public String getMessage() {
    return message;
  }

  public String execute() {
    if (System.currentTimeMillis() % 2 == 0) {
      message = "It's 0";
      return "zero";
    }

    message = "It's 1";
    return SUCCESS;
  }
}