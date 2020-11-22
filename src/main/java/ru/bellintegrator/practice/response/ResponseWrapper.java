package ru.bellintegrator.practice.response;

import lombok.Getter;
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
public class ResponseWrapper implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {

        if (body instanceof ResponseBodyMarker) {
            return body;
        }
        return new Wrapper<>(body);
    }

    @Getter
    public static class Wrapper<T>{

        private final T data;

        public Wrapper(T data) {
            this.data = data;
        }

    }
}
