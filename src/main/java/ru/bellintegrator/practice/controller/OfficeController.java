package ru.bellintegrator.practice.controller;

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
public class OfficeController {

    private final OfficeService officeService;

    /**
     * Получить список офисов с заданым фильтром
     * @param officeDto - фильтр для списка офисов
     * @return фильтрованный список офисов
     */
    @PostMapping("/list")
    public List<OfficeDto> getListOffice(@Validated(ListView.class) @RequestBody OfficeDto officeDto){

        return officeService.getList(officeDto);
    }

    /**
     * Получить офис по его идентификатору
     * @param id - идентификатор офиса
     * @return офис с заданым идентификатором
     */
    @GetMapping("/{id}")
    public OfficeDto getById(@Validated(FullView.class) @PathVariable long id) {

       return officeService.getById(id);
    }

    /**
     *Сохранить офис
     * @param officeDto - данные офиса для сохранения
     */
    @PostMapping("/save")
    public void save(@Validated(SaveView.class) @RequestBody OfficeDto officeDto){

        officeService.save(officeDto);
    }

    /**
     * Изменить данные офиса
     * @param officeDto - измененные данные офиса
     */
    @PostMapping("/update")
    public void update(@Validated(UpdateView.class) @RequestBody OfficeDto officeDto){

        officeService.update(officeDto);
    }
}
