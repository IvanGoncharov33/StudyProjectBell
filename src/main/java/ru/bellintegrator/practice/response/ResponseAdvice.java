package ru.bellintegrator.practice.response;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * Обработчик ответов с использованием классов-обёрток
 */
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {

    /**
     *Проверка поддержки
     * @param methodParameter параметры метода
     * @param aClass класс
     * @return логическое значение
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    /**
     * Обработка данных перед записью в тело ответа
     * @param body - данные из контроллера
     * @param methodParameter - параметры метода
     * @param mediaType - тип данных
     * @param aClass - класс
     * @param serverHttpRequest - НТТР запрос
     * @param serverHttpResponse - НТТЗ ответ
     * @return обернутые данные для записи в тело ответа
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body instanceof ErrorResponse) {
            return body;
        }if (body == null){
            return new SuccessResponseBody();
        }
        return new ResponseData(body);
    }
}
