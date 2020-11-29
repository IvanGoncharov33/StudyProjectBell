package ru.bellintegrator.practice.model.mapper;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 *{@inheritDoc}
 */
@AllArgsConstructor
@Service
public class DefaultMapperImpl implements DefaultMapper {

    private final MapperFactory mapperFactory;

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
    mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }
}
