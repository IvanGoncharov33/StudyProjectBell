package ru.bellintegrator.practice.dao.daoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.EmployeeDao;
import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.Employee;
import ru.bellintegrator.practice.model.Office;
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
public class EmployeeDaoImpl implements EmployeeDao {

    private final EntityManager entityManager;

    /**
     *{@inheritDoc}
     */
    @Override
    public List<Employee> getListEmployees(EmployeeDto employeeFilter) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Office> officeRoot = criteriaQuery.from(Office.class);
        ListJoin<Office, Employee> employeeList = officeRoot.joinList("employeeList");
        criteriaQuery.select(employeeList);
        Predicate criteria = criteriaBuilder.conjunction();

        if (employeeFilter.getOfficeId() != null){
            Predicate predicate = criteriaBuilder.equal(officeRoot.get("id"),
                    employeeFilter.getOfficeId());
            criteria =criteriaBuilder.and(criteria, predicate);
        }

        if (employeeFilter.getFirstName() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("firstName"),
                    employeeFilter.getFirstName());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (employeeFilter.getMiddleName() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("middleName"),
                    employeeFilter.getMiddleName());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (employeeFilter.getLastName() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("lastName"),
                    employeeFilter.getLastName());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (employeeFilter.getPosition() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("position"),
                    employeeFilter.getPosition());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if (employeeFilter.getDocumentCode() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("document").get("documentType").get("code"),
                    employeeFilter.getDocumentCode());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        if(employeeFilter.getCitizenShipCode() != null){
            Predicate predicate = criteriaBuilder.equal(employeeList.get("country").get("code"),
                    employeeFilter.getCitizenShipCode());
            criteria = criteriaBuilder.and(criteria, predicate);
        }

        criteriaQuery.where(criteria);
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery(criteriaQuery);
        List<Employee> employeeListResult = employeeTypedQuery.getResultList();

        if(employeeListResult.size() == 0){
            throw new NotFoundEntityException("Не найдены сотрудники с заданными критериями");
        }else{
            return employeeListResult;
        }
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public Employee getById(Long id) {

        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Employee> criteriaQuery = criteriaBuilder.createQuery(Employee.class);
        Root<Employee> employeeRoot = criteriaQuery.from(Employee.class);
        criteriaQuery.where(criteriaBuilder.equal(employeeRoot.get("id"),
                criteriaBuilder.parameter(Long.class, "id")));
        TypedQuery<Employee> typedQuery = entityManager.createQuery(criteriaQuery);
        typedQuery.setParameter("id", id);
        return typedQuery.getSingleResult();
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void save(Employee employee, EmployeeDto employeeDto){

        Office office = getOfficeById(employeeDto);
        if (employee.getDocument() != null){
            employee.getDocument().setEmployee(employee);
            if (employee.getDocument().getDocumentType() != null) {
                if (employee.getDocument().getDocumentType().getCode() != null){
                    employee.getDocument().setDocumentType(getDocumentTypeByCode(employeeDto));
                } else if (employee.getDocument().getDocumentType().getName() != null) {
                    employee.getDocument().setDocumentType(getDocumentTypeByName(employeeDto));
                }
            }
        }

        if (employee.getCountry() != null){
            employee.setCountry(getCountryByCode(employeeDto));
        }
        office.getEmployeeList().add(employee);
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void update(Employee employee, EmployeeDto employeeDto) {

        Employee updateEmployee = getById(employee.getId());

       if (employeeDto.getOfficeId() != null){
            getOfficeById(employeeDto).getEmployeeList().add(updateEmployee);
        }

        if (employee.getFirstName() != null){
            updateEmployee.setFirstName(employee.getFirstName());
        }

        if (employee.getLastName() != null){
            updateEmployee.setLastName(employee.getLastName());
        }

        if (employee.getMiddleName() != null){
            updateEmployee.setMiddleName(employee.getMiddleName());
        }

        if (employee.getPosition() != null){
            updateEmployee.setPosition(employee.getPosition());
        }

        if (employee.getPhone() != null){
            updateEmployee.setPhone(employee.getPhone());
        }

        if (employee.getDocument() != null){

            if (updateEmployee.getDocument() != null){

                if (employee.getDocument().getDocumentNumber() != null){
                    updateEmployee.getDocument().setDocumentNumber(employee.getDocument().getDocumentNumber());
                }

                if (employee.getDocument().getDocumentDate() !=null){
                    updateEmployee.getDocument().setDocumentDate(employee.getDocument().getDocumentDate());
                }
            }else {
                employee.getDocument().setEmployee(updateEmployee);
                 updateEmployee.setDocument(employee.getDocument());
            }

            if (employee.getDocument().getDocumentType() != null & employee.getDocument().getDocumentType().getName() != null ){
                updateEmployee.getDocument().setDocumentType(getDocumentTypeByName(employeeDto));
            }
        }

        if (employee.getCountry() != null){
            updateEmployee.setCountry(getCountryByCode(employeeDto));
        }

        if (employee.getIsIdentified() != null){
            updateEmployee.setIsIdentified(employee.getIsIdentified());
        }
    }

    private Office getOfficeById(EmployeeDto employeeDto){

            TypedQuery<Office> officeTypedQuery = entityManager.createQuery("select off from  Office  off " +
                    "where off.id like :id", Office.class)
                    .setParameter("id", employeeDto.getOfficeId());
            return officeTypedQuery.getSingleResult();
    }

    private DocumentType getDocumentTypeByCode(EmployeeDto employeeDto){

        TypedQuery<DocumentType> officeTypedQuery = entityManager.createQuery("select doc from  DocumentType  doc " +
                "where doc.code like :code", DocumentType.class)
                .setParameter("code", employeeDto.getDocumentCode());
        return officeTypedQuery.getSingleResult();
    }

    private DocumentType getDocumentTypeByName (EmployeeDto employeeDto){

        TypedQuery<DocumentType> officeTypedQuery = entityManager.createQuery("select doc from  DocumentType  doc " +
                "where doc.name like :name", DocumentType.class)
                .setParameter("name", employeeDto.getDocumentName());
        return officeTypedQuery.getSingleResult();
    }

    private Country getCountryByCode(EmployeeDto employeeDto){

        TypedQuery<Country> countryTypedQuery = entityManager.createQuery("select  coun from Country  coun " +
                "where coun.code like  :code", Country.class)
                .setParameter("code", employeeDto.getCitizenShipCode());
        return  countryTypedQuery.getSingleResult();
    }
}
