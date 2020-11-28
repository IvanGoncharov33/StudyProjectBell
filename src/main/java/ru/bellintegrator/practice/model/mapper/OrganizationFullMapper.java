package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.dto.OrganizationFullDto;

import java.util.List;

@Service
public class OrganizationFullMapper implements Mapper<Organization, OrganizationFullDto> {


    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationFullMapper(MapperFactory mapperFactory){

        mapperFactory.classMap(Organization.class, OrganizationFullDto.class)
                .byDefault().register();

        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Organization toEntity(OrganizationFullDto view) {
        return mapperFacade.map(view, Organization.class);
    }

    @Override
    public OrganizationFullDto toView(Organization entity) {
        return mapperFacade.map(entity, OrganizationFullDto.class);
    }


    @Override
    public List<Organization> toEntityList(Iterable<OrganizationFullDto> views) {
        return mapperFacade.mapAsList(views, Organization.class );
    }

    @Override
    public List<OrganizationFullDto> toViewList(Iterable<Organization> entities) {
        return mapperFacade.mapAsList(entities, OrganizationFullDto.class);
    }
}
