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
 *{@inheritDoc}
 */
@AllArgsConstructor
@Service
public class OfficeService implements InstanceService<OfficeDto, OfficeDto> {

    private final OfficeDao officeDao;
    private final DefaultMapper defaultMapperImpl;

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public List<OfficeDto> getList(OfficeDto dto) {

        return defaultMapperImpl.mapAsList(officeDao.getAll(dto), OfficeDto.class);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public OfficeDto getById(Long id) {

        return defaultMapperImpl.map(officeDao.getById(id), OfficeDto.class);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void update(OfficeDto dto) {

        Office updatedOffice = defaultMapperImpl.map(dto, Office.class);
        officeDao.update(updatedOffice);
    }

    /**
     *{@inheritDoc}
     */
    @Transactional
    @Override
    public void save(OfficeDto dto) {

        Office office = defaultMapperImpl.map(dto, Office.class);
        officeDao.save(office, dto);
    }
}
