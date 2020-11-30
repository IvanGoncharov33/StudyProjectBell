package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import ru.bellintegrator.practice.dto.OrganizationFullDto;
import ru.bellintegrator.practice.dto.OrganizationShortDto;

import javax.transaction.Transactional;
import java.util.List;

/**
 *{@inheritDoc}
 */
@Service
@AllArgsConstructor
public class OrganizationService implements InstanceService<OrganizationFullDto, OrganizationShortDto> {

    private  final OrganizationDao organizationDao;
    private final DefaultMapper defaultMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional()
    public List<OrganizationShortDto> getList(OrganizationShortDto view) {

        Organization organization = defaultMapper.map(view, Organization.class);
        return defaultMapper.mapAsList(organizationDao.getListOfOrganizationsByFilter(organization), OrganizationShortDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationFullDto getById(Long id) {

        Organization organization = organizationDao.getById(id);
        return defaultMapper.map(organization, OrganizationFullDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFullDto view) {

        Organization updatedOrganization = defaultMapper.map(view, Organization.class);
        organizationDao.update(updatedOrganization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationFullDto view) {

        Organization organizationSaved = defaultMapper.map(view, Organization.class);
       organizationDao.save(organizationSaved);
    }
}
