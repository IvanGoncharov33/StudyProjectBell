package ru.bellintegrator.practice.controller;

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
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    /**
     * Получить список всех типов документов
     * @return список всех типов документов
     */
    @RequestMapping("/documents")
    public List<CatalogDto> getListDocumentType(){

        return documentTypeService.getListDocumentType();
    }
}
