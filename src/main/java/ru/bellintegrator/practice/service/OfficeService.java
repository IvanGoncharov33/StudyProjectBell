package ru.bellintegrator.practice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bellintegrator.practice.dao.OfficeDao;
import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.model.Office;
import ru.bellintegrator.practice.model.mapper.DefaultMapper;

import javax.transaction.Transactional;
import java.util.List;

/**
 *Класс сервисного слоя приложения,
 *  для Office
 */
@AllArgsConstructor
@Service
public class OfficeService{

    private final OfficeDao officeDao;
    private final DefaultMapper defaultMapperImpl;

    /**
     * Получение списка офисов
     * с применением фильтра
     * @param dto - фильтр для офисов
     * @return отфильтрованный список DTO офисов
     */
    @Transactional
    public List<OfficeDto> getList(OfficeDto dto) {

        return defaultMapperImpl.mapAsList(officeDao.getAll(dto), OfficeDto.class);
    }

    /**
     * Получение офиса с заданным идентификатором
     * @param id - идентификатор офиса
     * @return  DTO офиса с заданным идентификатором
     */
    @Transactional
    public OfficeDto getById(Long id) {

        return defaultMapperImpl.map(officeDao.getById(id), OfficeDto.class);
    }

    /**
     * Изменение данных действующего офиса
     * @param dto - изменяемые данные
     */
    @Transactional
    public void update(OfficeDto dto) {

        Office updatedOffice = defaultMapperImpl.map(dto, Office.class);
        officeDao.update(updatedOffice);
    }

    /**
     * Добавление нового офиса
     * @param dto - данные нового офиса
     */
    @Transactional
    public void save(OfficeDto dto) {

        Office office = defaultMapperImpl.map(dto, Office.class);
        officeDao.save(office, dto);
    }
}
