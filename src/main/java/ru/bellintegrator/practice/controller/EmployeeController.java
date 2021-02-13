package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.EmployeeDto;
import ru.bellintegrator.practice.dto.validation.group.ListView;
import ru.bellintegrator.practice.dto.validation.group.SaveView;
import ru.bellintegrator.practice.dto.validation.group.UpdateView;
import ru.bellintegrator.practice.service.EmployeeService;

import java.util.List;

/**
 * Класс контроллер для сотрудников
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
@Api(value = "/api/user", description = "Операции с данными сотрудников")
public class EmployeeController {

    private final EmployeeService employeeService;

    /**
     * Получить список сотрудников с применением фильтра
     * @param employeeDto - фильтр для списка сотрудников
     * @return отфильтрованный список сотруднков
     */
    @PostMapping("/list")
    @ApiOperation(value = "Выводит список сотрудников с заданным фильтром", httpMethod = "POST")
    public List<EmployeeDto> getListEmployees(@Validated(ListView.class) @RequestBody EmployeeDto employeeDto){
        return employeeService.getList(employeeDto);
    }

    /**
     * Получить сотрудника с заданным идентификатором
     * @param id - идентификатор сотрудника
     * @return сотрудника с заданным идентификатором
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Выводит данные сотрудника по заданному идентификатору", httpMethod = "GET")
    public EmployeeDto getById(@PathVariable long id) {
     return employeeService.getById(id);
    }

    /**
     * Сохранить нового сотрудника
     * @param employeeDto - данные нового сотрудника
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавляет данные нового сотрудника", httpMethod = "POST")
    public void save(@Validated(SaveView.class) @RequestBody EmployeeDto employeeDto){
        employeeService.save(employeeDto);
    }

    /**
     * Изменить существующего сотрудника
     * @param employeeDto - новые данные изменямого сотрудника
     */
    @PostMapping("/update")
    @ApiOperation(value = "Изменяет данные сотрудника с указанным идентификатором", httpMethod = "POST")
    public void update(@Validated(UpdateView.class) @RequestBody EmployeeDto employeeDto){
        employeeService.update(employeeDto);
    }
}
