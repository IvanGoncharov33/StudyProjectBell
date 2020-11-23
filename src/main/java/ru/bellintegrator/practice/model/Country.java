package ru.bellintegrator.practice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Класс справочник стран
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@ToString(of = {"name", "code"})
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поле название страны
     */
    @Column(
            length = 60,
            unique = true,
            nullable = false
    )
    private String name;

    /**
     * Поле код страны
     */
    @Column(
            length = 3,
            unique = true,
            nullable = false
    )
    private String code;

    /**
     * Конструктор - создание нового объекта <code>Country</code> c определенными значениями
     * @param name - инициализирует поле название страны
     * @param code - инициализирует поле код страны
     */
    public Country(String name, String code) {
        this.name = name;
        this.code = code;
    }
}
