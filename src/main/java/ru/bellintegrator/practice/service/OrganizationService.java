package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.model.mapper.Mapper;

import ru.bellintegrator.practice.view.OrganizationFullView;
import ru.bellintegrator.practice.view.OrganizationShortView;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class OrganizationService implements InstanceService<OrganizationFullView, OrganizationShortView> {


    private  final OrganizationDao organizationDao;
    private final Mapper<Organization, OrganizationFullView> mapper;
    private final Mapper<Organization, OrganizationShortView> shortViewMapper;

    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    @Transactional()
    public List<OrganizationShortView > list(OrganizationShortView view) {
        Organization organization = shortViewMapper.toEntity(view);
        return shortViewMapper.toViewList(organizationDao.findAll(organization));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationFullView getById(Long id) {
        Organization organization = organizationDao.findById(id);
        return mapper.toView(organization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void update(OrganizationFullView view) {
        Organization updatedOrganization = mapper.toEntity(view);
         organizationDao.update(updatedOrganization);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void save(OrganizationFullView view) {
       Organization organizationSaved = mapper.toEntity(view);
       organizationDao.save(organizationSaved);
    }
}
