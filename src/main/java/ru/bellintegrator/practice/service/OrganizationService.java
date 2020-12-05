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
 * Класс сервисного слоя приложения,
 * для Organization
 */
@Service
@AllArgsConstructor
public class OrganizationService{

    private final OrganizationDao organizationDao;
    private final DefaultMapper defaultMapperImpl;

    /**
     * Получение списка организаций
     * с применением фильтра
     * @param dto - фильтр для организаций
     * @return отфильтрованный список DTO организаций
     */
    @Transactional
    public List<OrganizationShortDto> getList(OrganizationShortDto dto) {

        Organization organization = defaultMapperImpl.map(dto, Organization.class);
        return defaultMapperImpl.mapAsList(organizationDao
                .getListOfOrganizationsByFilter(organization), OrganizationShortDto.class);
    }

    /**
     * Получение организации с заданным идентификатором
     * @param id - идентификатор организации
     * @return DTO организации с заданным идентификатором
     */
    @Transactional
    public OrganizationFullDto getById(Long id) {

        Organization organization = organizationDao.getById(id);
        return defaultMapperImpl.map(organization, OrganizationFullDto.class);
    }

    /**
     * Изменение данных действующей организации
     * @param dto - изменяемые данные организации
     */
    @Transactional
    public void update(OrganizationFullDto dto) {

        Organization updatedOrganization = defaultMapperImpl.map(dto, Organization.class);
        organizationDao.update(updatedOrganization);
    }

    /**
     * Добавление новой организации
     * @param dto - данные новой организации
     */
    @Transactional
    public void save(OrganizationFullDto dto) {

        Organization organizationSaved = defaultMapperImpl.map(dto, Organization.class);
       organizationDao.save(organizationSaved);
    }
}
