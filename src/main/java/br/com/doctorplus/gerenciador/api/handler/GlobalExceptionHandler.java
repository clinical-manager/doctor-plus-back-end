package br.com.doctorplus.gerenciador.api.handler;

import br.com.doctorplus.gerenciador.model.exception.AcessDeniedException;
import br.com.doctorplus.gerenciador.model.exception.GeneralException;
import br.com.doctorplus.gerenciador.model.exception.NegocioException;
import br.com.doctorplus.gerenciador.model.exception.NotFoundException;
import br.com.doctorplus.gerenciador.model.exception.ResponseError;
import br.com.doctorplus.gerenciador.model.exception.UnauthorizedException;
import br.com.doctorplus.gerenciador.model.utils.MessageUtil;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageUtil messageUtil;

    @ExceptionHandler(value = NegocioException.class)
    public ResponseEntity<ResponseError> negocioException(NegocioException e) {
        String message = messageUtil.getMessage(e.getMessage());
        ResponseError err = new ResponseError(message, OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<ResponseError> authentication(UnauthorizedException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.UNAUTHORIZED.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = AcessDeniedException.class)
    public ResponseEntity<ResponseError> acessDenied(AcessDeniedException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.FORBIDDEN.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<ResponseError> illegalArgumentException(IllegalArgumentException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<ResponseError> internalServerError(HttpServerErrorException.InternalServerError e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity<ResponseError> generalException(GeneralException e) {
        ResponseError err = new ResponseError(e.getMessage(), OffsetDateTime.now(), HttpStatus.INTERNAL_SERVER_ERROR.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<ResponseError> notFoundException(NotFoundException e) {
        String message = messageUtil.getMessage(e.getMessage());
        ResponseError err = new ResponseError(message, OffsetDateTime.now(), HttpStatus.NOT_FOUND.value(), null);
        log.error("Error: {}", err);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseError> validation(MethodArgumentNotValidException e, HttpServletRequest request) {
        List<String> msgErroValidation = new ArrayList<>();
        for (ObjectError x : e.getBindingResult().getAllErrors()) {
            msgErroValidation.add(x.getDefaultMessage());
        }
        ResponseError err = new ResponseError(null, OffsetDateTime.now(), HttpStatus.BAD_REQUEST.value(), msgErroValidation);
        log.error("Erro de validação: {}", err);
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

}
