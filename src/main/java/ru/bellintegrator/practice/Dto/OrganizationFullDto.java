package ru.bellintegrator.practice.Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bellintegrator.practice.Dto.validation.group.SaveView;
import ru.bellintegrator.practice.Dto.validation.group.UpdateView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Организация
 */
@NoArgsConstructor
@Setter
@Getter
@ToString
public class OrganizationFullDto {

    @NotNull(groups = {UpdateView.class},
            message = "нужно указать ID")
    private Long id;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "нужно указать имя организации")
    private String name;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "нужно указать полное имя организации" )
    private String fullName;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "нужно указать ИНН организации")
    @Size(max = 10, groups ={SaveView.class, UpdateView.class},
            message = "длинна ИНН должна быть не более 10 символов")
    private String inn;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "нужно указать КПП организации")
    @Size(max = 9, message = "длинна КПП должна быть не более 9 символом")
    private String kpp;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "нужно указать адрес организации")
    private String address;

    @Size(max = 11, groups ={SaveView.class, UpdateView.class} ,
            message = "длинна телефонного номера должна быть не более 11 символов")
    private String phone;
    private Boolean isActive;
}