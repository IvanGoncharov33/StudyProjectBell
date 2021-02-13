package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "/api", description = "Операции со справочником по странам")
public class CountryController {

    private final CountryService countryService;

    /**
     *Получить список всех стран
     * @return список всех стран
     */
    @GetMapping(value = "/countries")
    @ApiOperation(value = "Выводит справочник по странам", httpMethod = "GET")
    public List<CatalogDto> getListCountry(){

        return countryService.getList();
    }
}
