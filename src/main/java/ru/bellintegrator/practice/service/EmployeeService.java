package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.EmployeeDao;
import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.model.Employee;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Класс сервисного слоя приложения,
 * для Employee
 */
@AllArgsConstructor
@Service
public class EmployeeService{

    private final EmployeeDao employeeDao;
    private final DefaultMapper employeeMapperImpl;

    /**
     * Получение списка сотрудников
     * с применением фильтра
     * @param dto - фильтр
     * @return отфильтрованный список DTO сотрудников
     */
    @Transactional
    public List<EmployeeDto> getList(EmployeeDto dto) {

        return employeeMapperImpl.mapAsList(employeeDao.getListEmployees(dto), EmployeeDto.class);
    }

    /**
     * Получение сотрудника с заданным идентификатором
     * @param id - идентификатор сотрудника
     * @return DTO сотрудника с заданным идентификатором
     */
    @Transactional
    public EmployeeDto getById(Long id) {

        return employeeMapperImpl.map(employeeDao.getById(id), EmployeeDto.class);
    }

    /**
     * Изменение данных
     * действующего сотрудника
     * @param dto - измененные данные сотрудника
     */
    @Transactional
    public void update(EmployeeDto dto) {
      Employee employee =  employeeMapperImpl.map(dto, Employee.class);
      employeeDao.update(employee, dto);
    }

    /**
     * Добавление нового сотрудника
     * @param dto - данные нового сотрудника
     */
    @Transactional
    public void save(EmployeeDto dto) {
    Employee employee = employeeMapperImpl.map(dto, Employee.class);
    employeeDao.save(employee, dto);
    }
}
