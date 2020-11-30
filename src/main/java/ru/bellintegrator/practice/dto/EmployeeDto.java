package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.bellintegrator.practice.dto.validation.group.FullView;
import ru.bellintegrator.practice.dto.validation.group.ListView;
import ru.bellintegrator.practice.dto.validation.group.SaveView;
import ru.bellintegrator.practice.dto.validation.group.UpdateView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для сотрудников
 */
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDto {

    @JsonView({ListView.class, FullView.class})
    @NotNull(groups = {UpdateView.class}, message = "нужно ввести id сотрудника")
    private Long id;

    @JsonView({ListView.class, FullView.class})
    @NotBlank(groups = {SaveView.class, UpdateView.class}, message = "нужно ввести имя сотрудника")
    private String firstName;

    @JsonView({ListView.class, FullView.class})
    private String middleName;

    @JsonView({ListView.class, FullView.class})
    private String lastName;

    @JsonView({ListView.class, FullView.class})
    @NotBlank(groups = {SaveView.class, UpdateView.class}, message = "нужно ввести должность сотрудника")
    private String position;

    @JsonView({FullView.class})
    @Size(max = 11, message = "телефонный номер не может содержать больше 11 символов")
    private String phone;

    @JsonView({FullView.class})
    private String documentName;

    @JsonView({FullView.class})
    private String documentNumber;

    @JsonView({FullView.class})
    private String documentDate;

    @JsonView({FullView.class})
    private String citizenshipName;

    @JsonView({FullView.class})
    private String citizenShipCode;

    @JsonView({FullView.class})
    private Boolean isIdentified;

    @NotNull(groups = {ListView.class, SaveView.class},
            message = "нужно ввести id офиса, в котором работает сотрудник")
    private Long officeId;
}
