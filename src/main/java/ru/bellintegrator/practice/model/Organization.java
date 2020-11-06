package ru.bellintegrator.practice.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Version;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Объектное представление таблицы Organization
 */
@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Organization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(nullable = false)
    private String name;

    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

    @Column(
            nullable = false,
            unique = true,
            length = 10
    )
    private String inn;

    @Column(
            nullable = false,
            unique = true,
            length = 9
    )
    private String kpp;

    @Column(length = 11)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(
            mappedBy = "Organization",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<Office> officeList;

}
