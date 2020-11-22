package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import org.springframework.stereotype.Service;

@Service
public class PracticeMapperFactory extends AbstractFactoryBean<MapperFactory> {
    public PracticeMapperFactory() {
        setSingleton(false);
    }

    @Override
    public Class<?> getObjectType() {
        return MapperFactory.class;
    }

    @Override
    protected MapperFactory createInstance() {
        return new DefaultMapperFactory.Builder().constructorResolverStrategy(null).build();
    }
}