package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.Mapper;

import ru.bellintegrator.practice.dto.OrganizationFullDto;
import ru.bellintegrator.practice.dto.OrganizationShortDto;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationService implements InstanceService<OrganizationFullDto, OrganizationShortDto> {

    private  final OrganizationDao organizationDao;
    private final Mapper<Organization, OrganizationFullDto> mapper;
    private final Mapper<Organization, OrganizationShortDto> shortViewMapper;

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional()
    public List<OrganizationShortDto> list(OrganizationShortDto view) {
        Organization organization = shortViewMapper.toEntity(view);
        return shortViewMapper.toViewList(organizationDao.getListOfOrganizationsByFilter(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationFullDto getById(Long id) {
        Organization organization = organizationDao.getById(id);
        return mapper.toView(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFullDto view) {
        Organization updatedOrganization = mapper.toEntity(view);
         organizationDao.update(updatedOrganization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationFullDto view) {
       Organization organizationSaved = mapper.toEntity(view);
       organizationDao.save(organizationSaved);
    }
}
