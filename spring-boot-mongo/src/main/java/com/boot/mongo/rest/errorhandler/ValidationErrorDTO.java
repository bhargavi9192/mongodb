package com.boot.mongo.rest.errorhandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ValidationErrorDTO {

    private final List<ErrorDTO> fieldErrors = new ArrayList<>();

    public ValidationErrorDTO() {

    }

    public void addFieldError(String path, String message) {
        ErrorDTO error = new ErrorDTO(path, message);
        fieldErrors.add(error);
    }

    public List<ErrorDTO> getFieldErrors() {
		return Collections.unmodifiableList(fieldErrors);
    }
}
