package ru.bellintegrator.practice.dao;

import ru.bellintegrator.practice.dto.OfficeDto;
import ru.bellintegrator.practice.model.Office;

import java.util.List;
/**
 *DAO для работы с Office
 */
public interface OfficeDao {

        /**
         * Получить объекты Office
         * @return список офисов
         */
        List<Office> getAll(OfficeDto officeDto);

        /**
         * Получить Office по идентификатору
         * @param id идентификатор офиса
         * @return офис с заданным идентификатором
         */
        Office getById(Long id);

        /**
         * Сохранить Office
         * @param office - сохраняемый офис
         */
        void save(Office office, OfficeDto officeDto);

        /**
         * Изменить Office
         * @param office - изменяемый офис
         */
        void update(Office office);
    }
