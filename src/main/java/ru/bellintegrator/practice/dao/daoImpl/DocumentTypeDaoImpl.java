package ru.bellintegrator.practice.dao.daoImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.bellintegrator.practice.dao.CatalogDao;
import ru.bellintegrator.practice.model.DocumentType;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Repository
@AllArgsConstructor
public class DocumentTypeDaoImpl implements CatalogDao<DocumentType> {

    private final EntityManager entityManager;

    /**
     * {@inheritDoc}
     */
    @Override
    public List<DocumentType> getAll() {

        Query query = entityManager.createQuery("select d from DocumentType d", DocumentType.class);
        List<DocumentType> documentTypeList =   (List<DocumentType>) query.getResultList();

        return documentTypeList;
    }
}
