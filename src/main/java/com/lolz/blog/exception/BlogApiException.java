package com.lolz.blog.exception;


import org.springframework.http.HttpStatus;

public class BlogApiException extends RuntimeException {
  private HttpStatus status;
  private String message;

  public BlogApiException(HttpStatus status, String message) {
    this.status = status;
    this.message = message;
  }
  public BlogApiException(String message,HttpStatus httpStatus, String message1) {
    super(message);
    this.status = httpStatus;
    this.message = message1;
  }


  public HttpStatus getHttpStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }

}
