package com.tungdadev.restaurant_management.exception;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:25
 */
public class ObjectAlreadyExistException extends RuntimeException {

    public ObjectAlreadyExistException(String name) {
        super(String.format("Object %s already exists", name));
    }
}
