package ru.bellintegrator.practice.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OrganizationDao;
import ru.bellintegrator.practice.model.Organization;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 *{@inheritDoc}
 */
@Repository
public class OrganizationDaoImpl implements OrganizationDao {
    private final EntityManager entityManager;


    @Autowired
    public OrganizationDaoImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }


    /**
     *{@inheritDoc}
     */
    @Override
    public List<Organization> getListOfOrganizationsByFilter(Organization organization) {


        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);

        List<Predicate> predicateList = new ArrayList<>();

        if(organization.getName() != null){
            predicateList.add(
                    criteriaBuilder.like(organizationRoot.get("name"), organization.getName())
            );
        }

        if (organization.getInn() != null){
            predicateList.add(
                    criteriaBuilder.like(organizationRoot.get("inn"), organization.getInn())
            );
        }

       if (organization.getIsActive() != null){
            predicateList.add(
                    criteriaBuilder.equal(organizationRoot.get("isActive"), organization.getIsActive())
            );
        }
        criteriaQuery.where(criteriaBuilder.and(predicateList.toArray(new Predicate[0])));
        TypedQuery<Organization> query = entityManager.createQuery(criteriaQuery);

        return query.getResultList();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Organization getById(Long id) {
       CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
       CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
       Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
       criteriaQuery.where(criteriaBuilder.equal(organizationRoot.get("id"), criteriaBuilder.parameter(Long.class, "id")));
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

        if (!organization.getName().equals(updOrganization.getName())){
            updOrganization.setName(organization.getName());
        }

        if (!organization.getFullName().equals(updOrganization.getFullName())){
            updOrganization.setFullName(organization.getFullName());
        }

        if (!organization.getInn().equals(updOrganization.getInn())){
            updOrganization.setInn(organization.getInn());
        }

        if (!organization.getKpp().equals(updOrganization.getKpp())){
            updOrganization.setKpp(organization.getKpp());
        }

        if (!organization.getAddress().equals(updOrganization.getAddress())){
            updOrganization.setAddress(organization.getAddress());
        }

        if (organization.getPhone() != null){
            updOrganization.setPhone(organization.getPhone());
        }

        if (organization.getIsActive() != null){
            updOrganization.setIsActive(organization.getIsActive());
        }
        entityManager.merge(updOrganization);
    }
}
