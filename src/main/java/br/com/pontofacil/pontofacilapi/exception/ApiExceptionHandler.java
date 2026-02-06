package br.com.pontofacil.pontofacilapi.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<ApiErrorResponse> regraNegocio(
            RegraNegocioException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.badRequest().body(

                new ApiErrorResponse(
                        LocalDateTime.now(),
                        400,
                        "Regra de negócio",
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(RecursoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> naoEncontrado(
            RecursoNaoEncontradoException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        404,
                        "Recurso não encontrado",
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(AcessoNegadoException.class)
    public ResponseEntity<ApiErrorResponse> acessoNegado(
            AcessoNegadoException ex,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        403,
                        "Acesso negado",
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiErrorResponse> erroGeral(
            Exception ex,
            HttpServletRequest request
    ){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        500,
                        "Erro interno do servidor",
                        ex.getMessage(),
                        request.getRequestURI()
                )
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String mensagem = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .findFirst()
                .orElse("Dados inválidos");

        return ResponseEntity.badRequest().body(
                new ApiErrorResponse(
                        LocalDateTime.now(),
                        400,
                        "Validação",
                        mensagem,
                        request.getRequestURI()
                )
        );
    }
}
