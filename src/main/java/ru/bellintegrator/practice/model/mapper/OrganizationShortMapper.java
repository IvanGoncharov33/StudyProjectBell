package ru.bellintegrator.practice.model.mapper;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.dto.OrganizationShortDto;

import java.util.List;

@AllArgsConstructor
@Service
public class OrganizationShortMapper implements Mapper<Organization, OrganizationShortDto> {

    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationShortMapper(MapperFactory mapperFactory){

        mapperFactory.classMap(Organization.class, OrganizationShortDto.class)
                .byDefault().register();

        mapperFacade = mapperFactory.getMapperFacade();
    }
    @Override
    public Organization toEntity(OrganizationShortDto view) {
        return  mapperFacade.map(view, Organization.class);
    }

    @Override
    public OrganizationShortDto toView(Organization entity) {
        return mapperFacade.map(entity, OrganizationShortDto.class);
    }

    @Override
    public List<Organization> toEntityList(Iterable<OrganizationShortDto> views) {
        return mapperFacade.mapAsList(views, Organization.class );
    }

    @Override
    public List<OrganizationShortDto> toViewList(Iterable<Organization> entities) {
        return mapperFacade.mapAsList(entities, OrganizationShortDto.class );
    }
}
