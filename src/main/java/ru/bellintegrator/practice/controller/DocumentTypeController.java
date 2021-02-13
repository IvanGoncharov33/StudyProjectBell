package ru.bellintegrator.practice.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.bellintegrator.practice.dto.CatalogDto;
import ru.bellintegrator.practice.service.DocumentTypeService;

import java.util.List;

/**
 * Класс-контроллер для справочника "Типы документов"
 */
@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Api(value = "/api",description = "Операции со справочником по типам документов")
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    /**
     * Получить список всех типов документов
     * @return список всех типов документов
     */
    @RequestMapping("/documents")
    @ApiOperation(value = "Выводит справочник по типам документов", httpMethod = "GET")
    public List<CatalogDto> getListDocumentType(){

        return documentTypeService.getListDocumentType();
    }
}
