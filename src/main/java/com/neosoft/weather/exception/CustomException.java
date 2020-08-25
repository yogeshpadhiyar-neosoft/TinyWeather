package com.neosoft.weather.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * custom exception
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends RuntimeException{
    private HttpStatus httpStatus;
    private List<String> errors;

}
