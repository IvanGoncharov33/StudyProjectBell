package ru.bellintegrator.practice.dao;


import ru.bellintegrator.practice.model.Organization;


import java.util.List;


/**
 *DAO для работы с Organization
 */
public interface OrganizationDao {
    /**
     * Получить все объекты Organization
     * @return список всех организаций
     */
   public List<Organization> findAll(Organization organization);

    /**
     * Получить Organization по идентификатору
     * @param id идентификатор организации
     * @return организацию с заданным идентификатором
     */
    Organization findById(Long id);

    /**
     * Сохранить Organization
     * @param organization - сохраняемая организация
     */
    long save(Organization organization);

    /**
     * Изменить Organization
     * @param organization - изменяемая организация
     */
    Long update(Organization organization);
}
