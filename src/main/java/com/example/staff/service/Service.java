package com.example.staff.service;

import com.example.staff.dto.AddForm;
import com.example.staff.dto.EmployeeDTO;
import com.example.staff.model.Employee;
import com.example.staff.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@org.springframework.stereotype.Service
@AllArgsConstructor
public class Service {
    private final EmployeeRepository repository;

    //Получить всех сотрудников и отсортировать
    public Page<EmployeeDTO> getAllEmployees(Pageable pageable, String sortBy){
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(sortBy));
        return repository.findAll(pageable).map(EmployeeDTO::from);
    }

    //Получить сотрудника по ID
    public void getEmployee(int id, Model model) {
        Employee employee = repository.getById(id);
        model.addAttribute("employee", EmployeeDTO.from(employee));
        if (employee.getPosition().equals("WORKER")) {
            model.addAttribute("managers", repository.findAllByPosition("MANAGER").stream().map(EmployeeDTO::from).collect(Collectors.toList()));
            if (employee.getManager_id() != null) {
                model.addAttribute("manager", EmployeeDTO.from(repository.getById(employee.getManager_id())));
            }
        } else {
            model.addAttribute("workers", repository.findEmployeeByManager_id(id).stream().map(EmployeeDTO::from).collect(Collectors.toList()));
        }
    }

    //Поменять должность сотрудника
    public void updateEmployee(int id) {
        Employee employee = repository.getById(id);
        if (employee.getPosition().equals("WORKER")) {
            employee.setPosition("MANAGER");
            employee.setManager_id(null);
        } else {
            employee.setPosition("WORKER");
            changeRelations(id);
        }
        repository.save(employee);
    }

    //Удалить привязку сотрудников к менеджеру после смены его должности
    private void changeRelations(int id) {
        List<Employee> employees = repository.findEmployeeByManager_id(id);
        if (employees != null)
            for (Employee employee : employees) {
                employee.setManager_id(null);
                repository.save(employee);
            }
    }

    //Привязать сотрудника к менеджеру
    public void changeRelation(int wId, int mId) {
        Employee employee = repository.getById(wId);
        employee.setManager_id(mId);
        repository.save(employee);
    }

    //Удалить сотрудника
    public void deleteEmployee(int id) {
        repository.deleteById(id);
    }

    //Проверить сотрудника на его наличие в базе
    public boolean existsById(int id) {
        return repository.existsById(id);
    }

    //Добавить сотрудника
    public void addEmployee(AddForm form) {
        Employee employee = Employee.builder()
                .surname(form.getSurname())
                .name(form.getName())
                .patronymic(form.getPatronymic())
                .dateOfBirth(LocalDate.parse(form.getDateOfBirth()))
                .dateOfHiring(LocalDate.parse(form.getDateOfHiring()))
                .position(form.getPosition())
                .build();
        repository.save(employee);
    }

    //Проеврить на корректность поля дат в форме добавления нового сотрудника
    public boolean dateHasErrors(AddForm form, RedirectAttributes attributes) {
        boolean result = false;
        if(isValid(form.getDateOfBirth()) && isValid(form.getDateOfHiring())) {
            if (!isPersonMature(form.getDateOfBirth())) {
                attributes.addFlashAttribute("personMature", "Человек не совершеннолетний");
                result = true;
            }
            if (isAfter(form.getDateOfBirth(), form.getDateOfHiring())) {
                attributes.addFlashAttribute("errorDate", "Дата рождения не может быть раньше или совпадать с датой приема на работу");
                result = true;
            }
        } else {
            attributes.addFlashAttribute("errorInputDate", "Введите верную дату");
            result = true;
        }
        return result;
    }

    //Проверка на корректность даты
    public boolean isValid(String dateStr) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.US);
        try {
            dateFormatter.parse(dateStr);
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    //Проверка даты рождения и приема на работу сотрудника на логическую последовательность
    public boolean isAfter(String date1, String date2) {
        try {
            LocalDate d1 = LocalDate.parse(date1);
            LocalDate d2 = LocalDate.parse(date2);
            if (d1.isAfter(d2) || d1.isEqual(d2)) {
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return false;
    }

    //Проверка на совершеннолетие добавляемого сотрудника
    public boolean isPersonMature(String birthDate){
        try {
            LocalDate d1 = LocalDate.parse(birthDate);
            LocalDate now = LocalDate.now();
            Period period = Period.between(d1, now);

            if (period.getYears() >= 16) {
                return true;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return false;
    }

}
