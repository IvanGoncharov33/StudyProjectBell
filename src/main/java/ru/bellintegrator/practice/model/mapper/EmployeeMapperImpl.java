package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.model.Employee;

import java.util.List;

/**
 *{@inheritDoc}
 */
@Service
public class EmployeeMapperImpl implements DefaultMapper {

    private final MapperFacade mapperFacade;

    @Autowired
    public EmployeeMapperImpl(MapperFactory mapperFactory){
        mapperFactory.classMap(Employee.class, EmployeeDto.class)
                .field("document.documentType.name", "documentName")
                .field("document.documentType.code", "documentCode")
                .field("document.documentNumber", "documentNumber")
                .field("document.documentDate", "documentDate")
                .field("country.name", "citizenShipName")
                .field("country.code", "citizenShipCode")
                .byDefault().register();
       mapperFacade = mapperFactory.getMapperFacade();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFacade.map(sourceObject, destinationClass);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFacade.map(sourceObject, destinationObject);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFacade.mapAsList(source, destinationClass);
    }
}
