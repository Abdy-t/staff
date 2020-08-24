package com.example.staff.dto;

import com.example.staff.model.Employee;
import lombok.*;

@Data
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EmployeeDTO {
    private Integer id;
    private String surname;
    private String name;
    private String patronymic;
    private String dateOfBirth;
    private String dateOfHiring;
    private String position;
    private Integer manager_id;

    public static EmployeeDTO from(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .surname(employee.getSurname())
                .name(employee.getName())
                .patronymic(employee.getPatronymic())
                .dateOfBirth(employee.getDateOfBirth().toString())
                .dateOfHiring(employee.getDateOfHiring().toString())
                .position(employee.getPosition())
                .manager_id(employee.getManager_id())
                .build();

    }
}
