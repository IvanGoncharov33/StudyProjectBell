package ru.bellintegrator.practice.response;

/**
 * Исключение выбрасывающееся в случае отсутствия искомой записи в базе данных.
 */
public class NotFoundEntityException extends RuntimeException {

    public NotFoundEntityException(String message) {
        super(message);
    }
}
