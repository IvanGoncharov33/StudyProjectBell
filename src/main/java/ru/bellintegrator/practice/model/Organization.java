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
 * Класс организаций
 */
@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString(of = {"name", "fullName","inn","kpp","address"})
public class Organization {

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
     * Поле полное название
     */
    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

    /**
     * Поле уникальный идентификационный налоговый номер
     */
    @Column(
            nullable = false,
            unique = true,
            length = 10
    )
    private String inn;

    /**
     * Поле код причины постановки на учет
     */
    @Column(
            nullable = false,
            unique = true,
            length = 9
    )
    private String kpp;

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
     * Поле действующая ли организация.
     */
    @Column(name = "is_active")
    private Boolean isActive;

    /**
     * Поле список офисов
     */
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JoinColumn(
            name = "Organization_id",
            nullable = false,
            unique = true
    )
   private List<Office> officeList;

    /**
     * Конструктор - создание нового объекта <code>Organization</code> c определенными значениями
     * @param name     - инициализирует поле название
     * @param fullName - инициализирует поле полное название
     * @param inn      - инициализирует поле уникальный идентификационный налоговый номер
     * @param kpp      - инициализирует поле код причины постановки на учет
     * @param address  - инициализирует поле адрес
     */
    public Organization(String name, String fullName, String inn, String kpp, String address) {
        this.name = name;
        this.fullName = fullName;
        this.inn = inn;
        this.kpp = kpp;
        this.address = address;
    }
}
