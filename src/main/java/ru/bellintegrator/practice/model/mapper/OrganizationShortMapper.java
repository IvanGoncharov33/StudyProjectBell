package ru.bellintegrator.practice.model.mapper;

import lombok.AllArgsConstructor;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.view.OrganizationShortView;

import java.util.List;

@AllArgsConstructor
@Service
public class OrganizationShortMapper implements Mapper<Organization, OrganizationShortView> {

    private final MapperFacade mapperFacade;

    @Autowired
    public OrganizationShortMapper(MapperFactory mapperFactory){

        mapperFactory.classMap(Organization.class, OrganizationShortView.class)
                .byDefault().register();

        mapperFacade = mapperFactory.getMapperFacade();
    }
    @Override
    public Organization toEntity(OrganizationShortView view) {
        return  mapperFacade.map(view, Organization.class);
    }

    @Override
    public OrganizationShortView toView(Organization entity) {
        return mapperFacade.map(entity, OrganizationShortView.class);
    }

    @Override
    public List<Organization> toEntityList(Iterable<OrganizationShortView> views) {
        return mapperFacade.mapAsList(views, Organization.class );
    }

    @Override
    public List<OrganizationShortView> toViewList(Iterable<Organization> entities) {
        return mapperFacade.mapAsList(entities, OrganizationShortView.class );
    }
}
