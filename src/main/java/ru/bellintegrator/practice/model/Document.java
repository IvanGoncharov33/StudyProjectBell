package ru.bellintegrator.practice.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Version;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Document {
    @Id
    private Long employeeId;

    @Version
    private Integer version;

    @Column(name = "document_name", nullable = false)
    private String documentName;

    @Column(name = "document_number", nullable = false, unique = true)
    private String documentNumber;

    @Column(name = "document_date", nullable = false, unique = true)
    private LocalDate documentDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    private Employee employee;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_type_id")
    private DocumentType documentType;

}
