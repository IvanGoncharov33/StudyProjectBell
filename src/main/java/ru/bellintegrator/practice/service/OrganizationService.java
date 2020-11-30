package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.dto.OrganizationFullDto;
import ru.bellintegrator.practice.dto.OrganizationShortDto;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import javax.transaction.Transactional;
import java.util.List;

/**
 *{@inheritDoc}
 */
@Service
@AllArgsConstructor
public class OrganizationService implements InstanceService<OrganizationFullDto, OrganizationShortDto> {

    private  final OrganizationDao organizationDao;
    private final DefaultMapper defaultMapperImpl;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationShortDto> getList(OrganizationShortDto view) {

        Organization organization = defaultMapperImpl.map(view, Organization.class);
        return defaultMapperImpl.mapAsList(organizationDao.getListOfOrganizationsByFilter(organization), OrganizationShortDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationFullDto getById(Long id) {

        Organization organization = organizationDao.getById(id);
        return defaultMapperImpl.map(organization, OrganizationFullDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFullDto view) {

        Organization updatedOrganization = defaultMapperImpl.map(view, Organization.class);
        organizationDao.update(updatedOrganization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationFullDto view) {

        Organization organizationSaved = defaultMapperImpl.map(view, Organization.class);
       organizationDao.save(organizationSaved);
    }
}
