package com.example.staff.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AddForm {
    @NotBlank
    @NotNull
    @Size(min = 2, max = 128, message = "Длина символов для фамилии должна быть ну  и <= 128")
    private String surname;
    @NotBlank
    @Size(min = 2, max = 128, message = "Длина символов для почты должна быть >= 4 и <= 128")
    private String name;
    @Size(max = 128, message = "Длина символов для отчества должна быть не больше 128 символов")
    private String patronymic;
    @NotBlank(message = "Заполните поле дата рождения")
    private String dateOfBirth;
    @NotBlank(message = "Заполните поле дата принятия на работу")
    private String dateOfHiring;
    @NotBlank(message = "Выберите должность")
    private String position ;
}
