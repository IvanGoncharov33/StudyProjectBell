package ru.bellintegrator.practice.model.mapper;

import java.util.List;

/**
 * Конвертер сущностей
 */
public interface DefaultMapper {

    /**
     * Преобразование sourceObject в экземпляр класса destinationClass
     *
     * @param sourceObject     исходный объект
     * @param destinationClass класс, в который надо преобразовать объект
     * @param <S>              тип исходного объекта
     * @param <D>              тип объекта, к который надо преобразовать исходный объект
     * @return экземпляр класса D с данными из sourceObject
     */
    <S, D> D map(S sourceObject, Class<D> destinationClass);

    /**
     * Запись данных из sourceObject в destinationObject
     *
     * @param sourceObject      исходный объект
     * @param destinationObject объект, в который надо преобразовать объект
     * @param <S>               тип исходного объекта
     * @param <D>               тип объекта, к который надо преобразовать исходный объект
     */
    <S, D> void map(S sourceObject, D destinationObject);

    /**
     * Преобразование коллекции оъектов
     *
     * @param source           исходный список
     * @param destinationClass класс, в который надо преобразовать объект
     * @param <S>              тип исходного объекта
     * @param <D>              тип объекта, к который надо преобразовать исходный объект
     * @return список элементов
     */
    <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass);
}
