package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.CatalogDao;
import ru.bellintegrator.practice.dto.CatalogDto;
import ru.bellintegrator.practice.model.DocumentType;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import java.util.List;

/**
 *Класс сервисного уровня приложения для справочника "Типы документов"
 */
@AllArgsConstructor
@Service
public class DocumentTypeService{

    private final DefaultMapper mapper;
    private final CatalogDao<DocumentType> catalogDao;

    /**
     * Пполучение списка всех типов документов
     * @return список DTO для типов документов.
     */
    public List<CatalogDto> getListDocumentType(){

       return mapper.mapAsList(catalogDao.getList(), CatalogDto.class);
    }
}
