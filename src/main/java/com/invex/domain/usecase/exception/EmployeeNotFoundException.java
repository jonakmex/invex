package com.invex.domain.usecase.exception;

public class EmployeeNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public EmployeeNotFoundException(Long id) {
        super("Employee with ID " + id + " not found.");
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
