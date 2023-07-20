package br.com.doctorplus.gerenciador.api.handler;

import br.com.doctorplus.gerenciador.model.exception.AcessDeniedException;
import br.com.doctorplus.gerenciador.model.exception.GeneralException;
import br.com.doctorplus.gerenciador.model.exception.NegocioException;
import br.com.doctorplus.gerenciador.model.exception.NotFoundException;
import br.com.doctorplus.gerenciador.model.exception.ResponseError;
import br.com.doctorplus.gerenciador.model.exception.UnauthorizedException;
import br.com.doctorplus.gerenciador.model.utils.MessageUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtil messageUtil;

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseError> validation(IllegalArgumentException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value());
        log.error("Argumento invalido: {}", err);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = NegocioException.class)
    public ResponseEntity<ResponseError> negocioException(NegocioException e) {
        String message = messageUtil.getMessage(e.getMessage());
        ResponseError err = new ResponseError(message, OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseError> authentication(UnauthorizedException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.UNAUTHORIZED.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AcessDeniedException.class)
    public ResponseEntity<ResponseError> acessDenied(AcessDeniedException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.FORBIDDEN.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ResponseError> generalException(GeneralException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseError> notFoundException(NotFoundException e) {
        String message = messageUtil.getMessage(e.getMessage());
        ResponseError err = new ResponseError(message, OffsetDateTime.now(), HttpStatus.NOT_FOUND.value());
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
