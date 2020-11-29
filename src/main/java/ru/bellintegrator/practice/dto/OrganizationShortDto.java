package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bellintegrator.practice.dto.validation.group.ListView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Сокращенный DTO организации
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationShortDto {

    @JsonView
    private Long id;

    @NotBlank(groups = {ListView.class},
            message = "нужно указать имя организации")
    @JsonView
    private String name;

    @Size(max = 10,
            message = "длинна ИНН должна быть не более 10")
    private String inn;

    @JsonView
    private Boolean isActive;
}
