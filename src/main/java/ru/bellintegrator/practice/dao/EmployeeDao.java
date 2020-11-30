package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.model.Employee;

import java.util.List;

/**
 * DAO для работы с Employee
 */
public interface EmployeeDao {

    /**
     * Получить объекты Employee c фильтром
     * @return список сотрудников с фильтром
     */
    List<Employee> getListEmployees(EmployeeDto employeeFilter);

    /**
     * Получить Employee по идентификатору
     * @param id идентификатор сотрудника
     * @return сотрудника с заданным идентификатором
     */
    Employee getById(Long id);

    /**
     * Сохранить Office
     * @param employee - сохраняемый сотрудник
     */
    void save(Employee employee, EmployeeDto employeeDto);

    /**
     * Изменить Office
     * @param employee - изменяемый сотрудник
     */
    void update(Employee employee, EmployeeDto employeeDto);
}
