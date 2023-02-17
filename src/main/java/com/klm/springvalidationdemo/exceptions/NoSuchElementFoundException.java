package com.klm.springvalidationdemo.exceptions;

public class NoSuchElementFoundException extends RuntimeException{
    public NoSuchElementFoundException(String message){
        super(message);
    }
}