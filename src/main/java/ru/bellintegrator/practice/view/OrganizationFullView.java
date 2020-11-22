package ru.bellintegrator.practice.view;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bellintegrator.practice.view.validation.group.SaveView;
import ru.bellintegrator.practice.view.validation.group.UpdateView;


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
public class OrganizationFullView {


    @NotNull(groups = {UpdateView.class},
            message = "Нужно указать ID")
    private Long id;


    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "Нужно указать имя организации")
    private String name;


    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "Нужно указать полное имя организации" )
    private String fullName;


    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "Нужно указать ИНН организации")
    @Size(max = 10, groups ={SaveView.class, UpdateView.class},
            message = "Длинна ИНН должна быть не более 10")
    private String inn;


    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "Нужно указать КПП организации")
    @Size(max = 9, message = "Длинна КПП должна быть не более 9")
    private String kpp;

    @NotBlank(groups = {SaveView.class, UpdateView.class},
            message = "Нужно указать адрес организации")
    private String address;

    @Size(max = 11, groups ={SaveView.class, UpdateView.class} ,
            message = "Длинна телефонного номера должна быть не более 11")
    private String phone;
    private Boolean isActive;
}