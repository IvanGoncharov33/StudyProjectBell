package ru.bellintegrator.practice.view;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import ru.bellintegrator.practice.view.validation.group.ListView;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrganizationShortView {

    @JsonView
    private Long id;

    @NotBlank(groups = {ListView.class})
    @JsonView
    private String name;

    @Size(max = 10)
    private String inn;

    @JsonView
    private Boolean isActive;
}
