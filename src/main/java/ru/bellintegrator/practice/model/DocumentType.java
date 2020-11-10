package ru.bellintegrator.practice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Класс типов документов удостоверяющих личность
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = {"name", "code"})
@ToString(of = {"name", "code"})
@Table(name = "document_type")
public class DocumentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле наименование документа
     */
    @Column(
            nullable = false,
            unique = true
    )
    private String name;

    /**
     * Поле код документа
     */
    @Column(
            length = 2,
            nullable = false,
            unique = true
    )
    private String  code;

    /**
     * Конструктор - создание нового объекта <code>DocumentType</code> c определенными значениями
     * @param name - инициализирует поле наименование документа
     * @param code - инициализирует поле код документа
     */
    public DocumentType(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
