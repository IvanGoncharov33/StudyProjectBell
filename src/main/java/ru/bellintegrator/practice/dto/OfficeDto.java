package ru.bellintegrator.practice.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bellintegrator.practice.dto.validation.group.FullView;
import ru.bellintegrator.practice.dto.validation.group.ListView;
import ru.bellintegrator.practice.dto.validation.group.SaveView;
import ru.bellintegrator.practice.dto.validation.group.UpdateView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * DTO для офиса
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OfficeDto {

    @NotNull(groups = UpdateView.class, message = "нужно ввести id офиса")
    @JsonView({ListView.class, FullView.class})
    private Long id;

    @NotNull(groups = {ListView.class, SaveView.class},
            message = "нужно ввести id организации, которой принадлежат офисы" )
    private Long organizationId;

    @NotBlank(groups = UpdateView.class, message = "нужно ввести имя офиса")
    @JsonView({ListView.class, FullView.class})
    private String name;

    @NotBlank(groups = UpdateView.class, message = "нужно ввести адрес офиса")
    @JsonView(FullView.class)
    private String address;

    @Size(max = 11, message = "телефон должен содержать не более 11 символов")
    @JsonView(FullView.class)
    private String phone;

    @JsonView({ListView.class, FullView.class})
    private Boolean isActive;
}
