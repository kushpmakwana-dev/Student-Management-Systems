package org.kushPmakwana.StudentManagement.exceptions;

public class TenantAlreadyExists extends RuntimeException {
    public TenantAlreadyExists(String message) {
        super(message);
    }
}
