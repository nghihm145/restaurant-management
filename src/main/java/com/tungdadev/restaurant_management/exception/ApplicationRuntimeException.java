package com.tungdadev.restaurant_management.exception;

/**
 * @author TungDaDev
 * @since 09/12/2024 - 10:24
 */
public class ApplicationRuntimeException extends RuntimeException {

    public ApplicationRuntimeException(String msg) {
        super(String.format("Error: %s", msg));
    }
}
