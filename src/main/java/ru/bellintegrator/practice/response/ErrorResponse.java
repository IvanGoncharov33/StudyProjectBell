package ru.bellintegrator.practice.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;



/**
 * Класс-обёртка сообщений об ошибках
 */
@AllArgsConstructor
@Setter
@Getter
public class ErrorResponse implements ResponseBodyMarker{
    private String error;
}
