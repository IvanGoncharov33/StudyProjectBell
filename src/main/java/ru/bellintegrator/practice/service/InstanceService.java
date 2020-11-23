package ru.bellintegrator.practice.service;

import java.util.List;

public interface InstanceService<F , S> {

    /**
     * Получить список сущностей по заданным критериям
     * @param view - критерии сущностей
     * @return список сущностей
     */
    List<S> list(S view);

    /**
     * Получить сущность с заданным идентификатором
     * @param id уникальный идентификатор офиса
     * @return сущность
     */
    F getById(Long id);

    /**
     * Обновить сведения о сущности
     * @param view сущность
     */
    void update(F view);

    /**
     * Добавить новую сущность
     * @param view сущность
     */
    void save(F view);
}
