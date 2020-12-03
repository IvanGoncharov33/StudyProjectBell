package ru.bellintegrator.practice.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 * Класс сотрудников
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"firstName", "lastName", "position"})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Поле имя
     */
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    /**
     * Поле отчество
     */
    @Column(name = "middle_name")
    private String middleName;

    /**
     * Поле фамилия
     */
    @Column(name = "second_name")
    private String lastName;

    /**
     * Поле должность
     */
    @Column(nullable = false)
    private String position;

    /**
     * Поле телефон
     */
    @Column(length = 11)
    private String phone;

    /**
     * Поле идентифицирован ли сотрудник
     */
    private Boolean isIdentified;

    /**
     * Поле документ удостоверяющий личность
     */
    @OneToOne(
            mappedBy = "employee",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            optional = false
    )
    private Document document;

    /**
     * Поле идентификационный номер страны
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "citizenship_id")
    private Country country;

    /**
     * Конструктор - создание нового объекта <code>Employee</code> c определенными значениями
     * @param firstName - инициализирует поле имя
     * @param position  - инициализирует поле должность
     */
    public Employee(String firstName, String position) {
        this.firstName = firstName;
        this.position = position;
    }
}

