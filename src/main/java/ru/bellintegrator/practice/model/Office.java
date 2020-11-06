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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * Объектное представление таблицы Organization
 */
@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Office {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Organization_id")
    private Organization organization;

    @Column(nullable = false)
    private String name;

    @Column(length = 11)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(
            mappedBy = "Office",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Employee> employeeList;

}
