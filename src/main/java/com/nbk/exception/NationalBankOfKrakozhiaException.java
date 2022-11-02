package com.nbk.exception;

// Single exception for simplicity
public class NationalBankOfKrakozhiaException extends RuntimeException {
    public NationalBankOfKrakozhiaException() {
    }

    public NationalBankOfKrakozhiaException(String message) {
        super(message);
    }

    public NationalBankOfKrakozhiaException(String message, Throwable cause) {
        super(message, cause);
    }
}
