package ru.bellintegrator.practice.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.CatalogDto;
import ru.bellintegrator.practice.service.CountryService;

import java.util.List;

/**
 * Класс контроллер для справочника "Страны"
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CountryController {

    private final CountryService countryService;

    /**
     *Получить список всех стран
     * @return список всех стран
     */
    @GetMapping("/countries")
    public List<CatalogDto> getListCountry(){

        return countryService.getList();
    }
}
