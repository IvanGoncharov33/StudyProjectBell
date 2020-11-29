package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.CatalogDao;
import ru.bellintegrator.practice.dto.CatalogDto;
import ru.bellintegrator.practice.model.Country;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import java.util.List;

/**
 * Класс сервисного слоя приложения для справочника "Страны"
 */
@AllArgsConstructor
@Service
public class CountryService{

    private final DefaultMapper defaultMapper;

    private final CatalogDao<Country> countryCatalogDao;

    /**
     *Получение списка стран
     * @return список DTO стран
     */
    public List<CatalogDto> getList(){

      return defaultMapper.mapAsList(countryCatalogDao.getList(), CatalogDto.class);
    }
}
