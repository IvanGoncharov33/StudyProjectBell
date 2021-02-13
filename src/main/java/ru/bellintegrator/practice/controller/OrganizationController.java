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
import ru.bellintegrator.practice.service.OrganizationService;
import ru.bellintegrator.practice.dto.OrganizationFullDto;
import ru.bellintegrator.practice.dto.OrganizationShortDto;
import ru.bellintegrator.practice.dto.validation.group.ListView;
import ru.bellintegrator.practice.dto.validation.group.SaveView;
import ru.bellintegrator.practice.dto.validation.group.UpdateView;

import java.util.List;

/**
 * Класс-контроллер для организации.
 */
@RestController
@AllArgsConstructor
@RequestMapping("/api/organization")
@Api(value = "/api/organization", description = "Операции с данными организаций")
public class OrganizationController {

   private final OrganizationService organizationService;

    /**
     * Получить список организаций по заданным критериям.
     * @param organizationShortDto - критерии для искомых организаций
     * @return список искомых организаций
     */
    @PostMapping("/list")
    @ApiOperation(value = "Выводит список организаций с заданным фильтром", httpMethod = "POST")
    public List<OrganizationShortDto>getListOrganization(@Validated(ListView.class)
                                                              @RequestBody OrganizationShortDto organizationShortDto){

        return organizationService.getList(organizationShortDto);
    }

    /**
     * Получить организацию по ee ID.
     * @param id - идентификатор организации
     * @return искомая организация.
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Выводит данные организации по заданному идентификатору", httpMethod = "GET")
    public OrganizationFullDto getOrgById(@PathVariable long id) {

        return organizationService.getById(id);
    }

    /**
     * Сохранить новую организацию.
     * @param organizationFullDto - данные сохраняемой организации
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавляет данные новой организации", httpMethod = "POST")
    public void save(@Validated(SaveView.class) @RequestBody OrganizationFullDto organizationFullDto){

        organizationService.save(organizationFullDto);
    }

    /**
     * Изменить заданную организацию.
     * @param organizationFullDto - данные изменяемой организации
     */
    @PostMapping("/update")
    @ApiOperation(value = "Изменяет данные организации с указанным идентификатором", httpMethod = "POST")
    public void update(@Validated(UpdateView.class) @RequestBody OrganizationFullDto organizationFullDto){

        organizationService.update(organizationFullDto);
    }
}
