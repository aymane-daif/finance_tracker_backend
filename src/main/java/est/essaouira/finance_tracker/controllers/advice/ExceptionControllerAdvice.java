package est.essaouira.finance_tracker.controllers.advice;

import est.essaouira.finance_tracker.exceptions.ExpenseNotFoundException;
import est.essaouira.finance_tracker.exceptions.IncomeNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


import java.time.LocalDateTime;
import java.util.Objects;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDetails> exceptionMethodArgumentNotValidHandler(
            MethodArgumentNotValidException ex) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(Objects.requireNonNull(ex.getFieldError()).getDefaultMessage())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(IncomeNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionIncomeNotFoundHandler(IncomeNotFoundException exception) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }

    @ExceptionHandler(ExpenseNotFoundException.class)
    public ResponseEntity<ErrorDetails> exceptionExpenseNotFoundHandler(ExpenseNotFoundException exception) {
        ErrorDetails errorDetails = ErrorDetails.builder()
                .timestamp(LocalDateTime.now())
                .message(exception.getMessage())
                .build();
        return ResponseEntity
                .badRequest()
                .body(errorDetails);
    }
}