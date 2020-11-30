package ru.bellintegrator.practice.dao.daoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.Organization;
import ru.bellintegrator.practice.response.NotFoundEntityException;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 *{@inheritDoc}
 */
@AllArgsConstructor
@Repository
public class OfficeDaoImpl implements OfficeDao {

    private final EntityManager entityManager;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Office> getAll(OfficeDto filterOffice) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Organization> organizationRoot = criteriaQuery.from(Organization.class);
        ListJoin<Organization, Office> officeList = organizationRoot.joinList("officeList");
        criteriaQuery.select(officeList);
        Predicate criteria = criteriaBuilder.conjunction();

        if(filterOffice.getOrganizationId() != null){
            Predicate predicate = criteriaBuilder.equal(organizationRoot.get("id"), filterOffice.getOrganizationId());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (filterOffice.getName() != null) {
            Predicate predicate = criteriaBuilder.equal(officeList.get("name"), filterOffice.getName());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if(filterOffice.getPhone() != null){
            Predicate predicate = criteriaBuilder.equal(officeList.get("phone"), filterOffice.getPhone());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (filterOffice.getIsActive() != null){
            Predicate predicate = criteriaBuilder.equal(officeList.get("isActive"), filterOffice.getIsActive());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        criteriaQuery.where(criteria);
        TypedQuery<Office> query = entityManager.createQuery(criteriaQuery);
        List<Office> officeListResult = query.getResultList();

        if (officeListResult.size() == 0){
            throw new NotFoundEntityException("Не найдены офисы с заданными критериями");
        }else{
            return officeListResult;
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Office getById(Long id) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Office> criteriaQuery = criteriaBuilder.createQuery(Office.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        criteriaQuery.where(criteriaBuilder.equal(officeRoot.get("id"), criteriaBuilder.parameter(Long.class, "id")));
        TypedQuery<Office> officeQuery = entityManager.createQuery(criteriaQuery);
        officeQuery.setParameter("id", id);
        return officeQuery.getSingleResult();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void save(Office office, OfficeDto officeDto) {

        TypedQuery<Organization> typedQuery = entityManager.createQuery("select org from Organization org where org.id like :id", Organization.class)
                .setParameter("id", officeDto.getOrganizationId());
        Organization organization = typedQuery.getSingleResult();
        organization.getOfficeList().add(office);
        entityManager.merge(organization);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void update(Office office) {

        Office updatedOffice = getById(office.getId());

        if(office.getName() != null){
            updatedOffice.setName(office.getName());
        }

        if(office.getAddress() != null){
            updatedOffice.setAddress(office.getAddress());
        }

        if(office.getPhone() != null){
            updatedOffice.setPhone(office.getPhone());
        }

        if(office.getPhone() != null){
            updatedOffice.setIsActive(office.getIsActive());
        }

        entityManager.merge(updatedOffice);
    }
}
