package com.tungdadev.restaurant_management.exception;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:25
 */
public class ObjectNotFoundException extends RuntimeException {

    public ObjectNotFoundException(String name) {
        super(String.format("Object %s not found", name));
    }
}
