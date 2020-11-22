package ru.bellintegrator.practice.model.mapper;

import java.util.List;

public interface Mapper<E, V> {

    E toEntity(V view);

    V toView(E entity);

    List<E> toEntityList(Iterable<V> views);

    List<V> toViewList(Iterable<E> entities);
}
