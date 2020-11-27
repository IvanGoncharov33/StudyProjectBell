package ru.bellintegrator.practice.response;

import lombok.Getter;

/**
 * Класс-обёртка сообщений об успешном завершении запросов
 */
@Getter
public class SuccessResponseBody {
    private final String result = "success";
}
