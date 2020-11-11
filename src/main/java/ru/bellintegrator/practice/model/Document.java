package ru.bellintegrator.practice.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Version;


/**
 * Класс документов удостоверяющих личность
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"documentName", "documentNumber", "documentDate"})
public class Document {
    @Id
    private Long employeeId;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Поле наименование документа
     */
    @Column(name = "document_name", nullable = false)
    private String documentName;

    /**
     * Поле номер документа
     */
    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    /**
     * Поле дата выдачи документа
     */
    @Column(name = "document_date", nullable = false, unique = true)
    private String documentDate;

    /**
     * Поле сотрудник, которому принадлежит документ
     */
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    /**
     * Поле идентификационный номер типа документа
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    /**
     * Конструктор - создание нового объекта <code>Document</code> c определенными значениями
     * @param documentName   - инициализирует поле наименование документа
     * @param documentNumber - инициализирует поле номер документа
     * @param documentDate   - инициализирует поле дата выдачи документа
     */
    public Document(String documentName, String documentNumber, String documentDate) {
        this.documentName = documentName;
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
    }
}
