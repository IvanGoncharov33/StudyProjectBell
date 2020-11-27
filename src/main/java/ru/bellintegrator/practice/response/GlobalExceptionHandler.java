package ru.bellintegrator.practice.response;

import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Глобальный обработчик исключений
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    /**
     * Обработчик исключений валидации DTO
     * @param exception - выбрасываемое исключение
     * @param headers - НТТР заголовок
     * @param status - НТТР статус
     * @param request - НТТР запрос
     * @return информацию об ошибке
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        String errorCode = generatedLogCode();
        ErrorResponse errorResponse = new ErrorResponse();

        log.error(
                "Error Code = [" + errorCode + "] message: " + exception.getMessage() , exception
        );

        for (ObjectError error:exception.getBindingResult().getAllErrors()) {

            if (errorResponse.getError() == null){
                errorResponse.setError(
                        "Error Code = [" + errorCode + "] message: " +  error.getDefaultMessage()
                );
            }else{
                errorResponse.setError(errorResponse.getError() + " , " + error.getDefaultMessage());
            }
        }
        return new ResponseEntity<>(errorResponse, headers, status);
    }

    /**
     * Обработчик кастомного исключения
     * @param exception - выбрасываемое исключение
     * @return информацию об ошибке
     */
    @ExceptionHandler(NotFoundEntityException.class)
    @ResponseBody
    protected ResponseEntity<?>  handleNotFoundExceptions(NotFoundEntityException exception){

        String errorCode = generatedLogCode();
        ErrorResponse errorResponse = new ErrorResponse(
                "Error Code = [" + errorCode + "] message: " + exception.getMessage()
        );
        log.error("Error Code = [" + errorCode +
                "] message: " + exception.getMessage() , exception);
        return new  ResponseEntity<>(errorResponse ,  HttpStatus.NOT_FOUND);
    }

    /**
     * Обработчик ошибок
     * @param exception - выбрасываемое исключение
     * @return информацию об ошибке
     */
   @ExceptionHandler(Throwable.class)
    protected ResponseEntity<?> handleAllExceptions(Throwable exception){

       String errorCode = generatedLogCode();
       ErrorResponse errorResponse = new ErrorResponse(
               "Error Code = [" + errorCode + "] message: внутреняя ошибка сервера"
       );
       log.error("Error Code = [" + errorCode +
               "] message - " + exception.getMessage() , exception);

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String generatedLogCode(){
        return RandomString.make(5);
    }

}
