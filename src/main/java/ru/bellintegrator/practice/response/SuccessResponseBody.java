package ru.bellintegrator.practice.response;

import lombok.Getter;

/**
 * Класс-обёртка сообщений об успешном завершении запросов
 */
@Getter
public class SuccessResponseBody implements ResponseBodyMarker {

    public static final SuccessResponseBody SUCCESS_RESPONSE_BODY = new SuccessResponseBody();

    private final String result = "success";


}
