package ru.bellintegrator.practice.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;
import java.util.List;

/**
 * Класс офисов
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString(of = {"name","address"})
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Служебное поле Hibernate
     */
    @Version
    private Integer version;

    /**
     * Поле название
     */
    @Column(nullable = false)
    private String name;

    /**
     * Поле телефон
     */
    @Column(length = 11)
    private String phone;

    /**
     * Поле адрес
     */
    @Column(nullable = false)
    private String address;

    /**
     * Поле рабочий ли офис
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Поле список работников офиса
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "office_id",
            nullable = false
    )
    private List<Employee> employeeList;

    /**
     * Конструктор - создание нового объекта <code>Office</code> c определенными значениями
     * @param name    - инициализирует поле название
     * @param address - инициализирует поле адрес
     */
    public Office(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
