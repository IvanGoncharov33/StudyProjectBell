package ru.bellintegrator.practice.dao;


import ru.bellintegrator.practice.model.Organization;


import java.util.List;


/**
 *DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить все объекты Organization
     * @param organization - критерий отбора организаций
     * @return список организаций отобранных по критерию
     */
   public List<Organization> getListOfOrganizationsByFilter(Organization organization);

    /**
     * Получить Organization по идентификатору
     * @param id идентификатор организации
     * @return организацию с заданным идентификатором
     */
    Organization getById(Long id);

    /**
     * Сохранить Organization
     * @param organization - сохраняемая организация
     */
    void save(Organization organization);

    /**
     * Изменить Organization
     * @param organization - изменяемая организация
     */
    void update(Organization organization);
}
