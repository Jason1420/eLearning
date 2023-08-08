package com.elearning.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Exception400 extends RuntimeException {
    public Exception400(String message) {
        super(message);
    }
}
