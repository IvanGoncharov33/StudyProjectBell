package ru.bellintegrator.practice.service;

import java.util.List;

/**
 * Интерфейс сервисного уровня приложения
 * @param <F> - DTO сущность
 * @param <S> - DTO - фильтр для сущностей
 */
public interface InstanceService<F , S> {

    /**
     * Получить список сущностей по фильтру
     * @param dto - фильтр сущностей
     * @return список DTO сущностей
     */
    List<S> list(S dto);

    /**
     * Получить сущность с заданным идентификатором
     * @param id уникальный идентификатор сущности
     * @return DTO c заданным идентификатором
     */
    F getById(Long id);

    /**
     * Обновить сведения о сущности
     * @param dto DTO с данными для изменения
     */
    void update(F dto);

    /**
     * Добавить новую сущность
     * @param dto DTO для сохранения
     */
    void save(F dto);
}
