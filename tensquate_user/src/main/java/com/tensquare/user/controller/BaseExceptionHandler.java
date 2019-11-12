package com.tensquare.user.controller;


import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
公共异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result errorException(Exception e){
        e.printStackTrace();
        return new Result(true, StatusCode.ERROR,e.getMessage());
    }




}
