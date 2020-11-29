package ru.bellintegrator.practice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotNull;

/**
 * DTO для справочников
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CatalogDto {

    @NotNull
    private String name;

    @NotNull
    private String code;
}
