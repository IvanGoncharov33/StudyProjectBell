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
    public List<Organization> findAll(Organization organization) {

        if (organization == null){
            throw new IllegalArgumentException("organization can not be null");
        }
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Organization> criteriaQuery = criteriaBuilder.createQuery(Organization.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        criteriaQuery.select(organizationRoot);

        List<Predicate> predicateList = new ArrayList<>();

        if(organization.getName() != null){
            predicateList.add(
                    criteriaBuilder.equal(organizationRoot.get("name"), organization.getName())
            );
        }

        if (organization.getInn() != null){
            predicateList.add(
                    criteriaBuilder.like(organizationRoot.get("inn"), organization.getInn())
            );
        }

        if (organization.getInn() != null){
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
    public Organization findById(Long id) {
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
    public long save(Organization organization) {
        System.out.println(organization.toString());
        entityManager.persist(organization);
        return organization.getId();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Long update(Organization organization) {

       Organization organization1 = entityManager.merge(organization);
       return organization1.getId();
    }
}
