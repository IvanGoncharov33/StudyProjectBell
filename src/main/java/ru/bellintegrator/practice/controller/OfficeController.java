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
import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.dto.validation.group.FullView;
import ru.bellintegrator.practice.dto.validation.group.ListView;
import ru.bellintegrator.practice.dto.validation.group.SaveView;
import ru.bellintegrator.practice.dto.validation.group.UpdateView;
import ru.bellintegrator.practice.service.OfficeService;

import java.util.List;

/**
 * Класс контроллер для офисов
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api/office")
@Api(value = "/api/office", description = "Операции с данными офисов")
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Получить список офисов с заданым фильтром
     * @param officeDto - фильтр для списка офисов
     * @return фильтрованный список офисов
     */
    @PostMapping("/list")
    @ApiOperation(value = "Выводит список офисов с заданным фильтром", httpMethod = "POST")
    public List<OfficeDto> getListOffice(@Validated(ListView.class) @RequestBody OfficeDto officeDto){

        return officeService.getList(officeDto);
    }

    /**
     * Получить офис по его идентификатору
     * @param id - идентификатор офиса
     * @return офис с заданым идентификатором
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "Выводит данные офиса по заданному идентификатору", httpMethod = "GET")
    public OfficeDto getById(@Validated(FullView.class) @PathVariable long id) {

       return officeService.getById(id);
    }

    /**
     *Сохранить офис
     * @param officeDto - данные офиса для сохранения
     */
    @PostMapping("/save")
    @ApiOperation(value = "Добавляет данные нового офиса", httpMethod = "POST")
    public void save(@Validated(SaveView.class) @RequestBody OfficeDto officeDto){

        officeService.save(officeDto);
    }

    /**
     * Изменить данные офиса
     * @param officeDto - измененные данные офиса
     */
    @PostMapping("/update")
    @ApiOperation(value = "Изменяет данные офиса с указанным идентификатором", httpMethod = "POST")
    public void update(@Validated(UpdateView.class) @RequestBody OfficeDto officeDto){

        officeService.update(officeDto);
    }
}
