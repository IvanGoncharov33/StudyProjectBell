package ru.bellintegrator.practice.model.mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationFullView;

import java.util.List;

@Service
public class OrganizationFullMapper implements Mapper<Organization, OrganizationFullView> {


    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationFullMapper(MapperFactory mapperFactory){

        mapperFactory.classMap(Organization.class, OrganizationFullView.class)
                .byDefault().register();

        mapperFacade = mapperFactory.getMapperFacade();
    }

    @Override
    public Organization toEntity(OrganizationFullView view) {
        return mapperFacade.map(view, Organization.class);
    }

    @Override
    public OrganizationFullView toView(Organization entity) {
        return mapperFacade.map(entity, OrganizationFullView.class);
    }


    @Override
    public List<Organization> toEntityList(Iterable<OrganizationFullView> views) {
        return mapperFacade.mapAsList(views, Organization.class );
    }

    @Override
    public List<OrganizationFullView> toViewList(Iterable<Organization> entities) {
        return mapperFacade.mapAsList(entities, OrganizationFullView.class);
    }
}
