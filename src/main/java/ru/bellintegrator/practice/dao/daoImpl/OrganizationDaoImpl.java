package ru.bellintegrator.practice.dao.daoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.response.NotFoundEntityException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *{@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class OrganizationDaoImpl implements OrganizationDao {

    private final EntityManager entityManager;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Organization> getListOfOrganizationsByFilter(Organization organization) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);

        Predicate criteria = criteriaBuilder.conjunction();

        if(organization.getName() != null){

        Predicate predicate = criteriaBuilder.equal(organizationRoot.get("name"), organization.getName());
        criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (organization.getInn() != null){

           Predicate predicate =  criteriaBuilder.like(organizationRoot.get("inn"), organization.getInn());
           criteria = criteriaBuilder.and(criteria, predicate);
        }

       if (organization.getIsActive() != null){

           Predicate predicate = criteriaBuilder.equal(organizationRoot.get("isActive"), organization.getIsActive());
           criteria = criteriaBuilder.and(criteria, predicate);
        }

        criteriaQuery.where(criteria);
        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        List<Organization> organizationList = query.getResultList();
        if (organizationList.size() == 0){
            throw new NotFoundEntityException("Не найдена организация с заданными критериями");
        }else{
            return organizationList;
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Organization getById(Long id) {

       CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
       Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
       criteriaQuery.where(criteriaBuilder.equal(organizationRoot.get("id"),
               criteriaBuilder.parameter(Long.class, "id")));
        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void save(Organization organization) {
        entityManager.persist(organization);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void  update(Organization organization) {

        Organization updOrganization = getById(organization.getId());

        if (organization.getName() != null){
            updOrganization.setName(organization.getName());
        }

        if (organization.getFullName() != null){
            updOrganization.setFullName(organization.getFullName());
        }

        if (organization.getInn() != null){
            updOrganization.setInn(organization.getInn());
        }

        if (organization.getKpp()!= null){
            updOrganization.setKpp(organization.getKpp());
        }

        if (organization.getAddress()!= null){
            updOrganization.setAddress(organization.getAddress());
        }

        if (organization.getPhone() != null){
            updOrganization.setPhone(organization.getPhone());
        }

        if (organization.getIsActive() != null){
            updOrganization.setIsActive(organization.getIsActive());
        }
    }
}
