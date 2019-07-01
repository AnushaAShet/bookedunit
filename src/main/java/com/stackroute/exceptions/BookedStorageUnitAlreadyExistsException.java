package com.stackroute.exceptions;

public class BookedStorageUnitAlreadyExistsException extends Exception{

    String message;
    public BookedStorageUnitAlreadyExistsException(String message) {
        super(message);
        this.message = message;
    }
}

