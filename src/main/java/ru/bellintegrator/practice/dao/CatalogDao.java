package ru.bellintegrator.practice.dao;

import java.util.List;

/**
 * DAO для работы со справочниками
 * @param <E> - тип справочника
 */
public interface CatalogDao<E> {

    /**
     * Получить всю справочную информацию
     * @return список справочной информации
     */
    List<E> getAll();
}
