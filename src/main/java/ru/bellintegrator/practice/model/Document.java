package ru.bellintegrator.practice.model;

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
@ToString(of = {"documentNumber", "documentDate"})
public class Document {
    @Id
    @Column(name = "employee_id", nullable = false, unique = true)
    private Long employeeId;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Поле номер документа
     */
    @Column(name = "document_number", unique = true)
    private String documentNumber;

    /**
     * Поле дата выдачи документа
     */
    @Column(name = "document_date", unique = true)
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
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

    /**
     * Конструктор - создание нового объекта <code>Document</code> c определенными значениями
     * @param documentNumber - инициализирует поле номер документа
     * @param documentDate   - инициализирует поле дата выдачи документа
     * @param employee - инициализирует поле сотрудник
     */
    public Document( String documentNumber, String documentDate, Employee employee) {
        this.documentNumber = documentNumber;
        this.documentDate = documentDate;
        this.employee = employee;
    }
}
