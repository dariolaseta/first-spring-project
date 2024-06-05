package com.tutorial.demo.exceptions;

public class StudentCRUDException extends RuntimeException{

    public StudentCRUDException(String message) {
        super(message);
    }
}
