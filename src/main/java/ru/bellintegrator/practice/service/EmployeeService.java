package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.EmployeeDao;
import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.model.Employee;
import ru.bellintegrator.practice.model.mapper.EmployeeMapperImpl;

import javax.transaction.Transactional;
import java.util.List;

/**
 *{@inheritDoc}
 */
@AllArgsConstructor
@Service
public class EmployeeService implements InstanceService<EmployeeDto, EmployeeDto> {

    private final EmployeeDao employeeDao;
    private final EmployeeMapperImpl employeeMapper;

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public List<EmployeeDto> getList(EmployeeDto dto) {

        return employeeMapper.mapAsList(employeeDao.getListEmployees(dto), EmployeeDto.class);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public EmployeeDto getById(Long id) {

        return employeeMapper.map(employeeDao.getById(id), EmployeeDto.class);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void update(EmployeeDto dto) {
      Employee employee =  employeeMapper.map(dto, Employee.class);
      employeeDao.update(employee);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void save(EmployeeDto dto) {
    Employee employee = employeeMapper.map(dto, Employee.class);
    employeeDao.save(employee);
    }
}
