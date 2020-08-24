package com.example.staff.controller;

import com.example.staff.dto.AddForm;
import com.example.staff.service.Service;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@org.springframework.stereotype.Controller
@AllArgsConstructor
public class Controller {
    private final Service service;

    //Отображение главной страницы отсортированной по фамилии
    @GetMapping
    public String getMainPage(@PageableDefault(sort = {"id"}, direction = Sort.Direction.ASC) Model model,
                              Authentication authentication, @PageableDefault(value = 5) Pageable pageable) {
        model.addAttribute("employees",service.getAllEmployees(pageable, "surname"));
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("url", "/");
        return "main-page";
    }

    //Отображение главной страницы отсортированной по имени
    @GetMapping("/name")
    public String sortByName(Model model, @PageableDefault(value = 5) Pageable pageable, Authentication authentication) {
        model.addAttribute("employees",service.getAllEmployees(pageable, "name"));
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("url", "/name");
        return "main-page";
    }

    //Отображение главной страницы отсортированной по отчеству
    @GetMapping("/patronymic")
    public String sortByPatronymic(Model model, @PageableDefault(value = 5) Pageable pageable, Authentication authentication) {
        model.addAttribute("employees",service.getAllEmployees(pageable, "patronymic"));
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("url", "/patronymic");
        return "main-page";
    }

    //Отображение главной страницы отсортированной по дате рождения
    @GetMapping("/birth")
    public String sortBySurname(Model model, @PageableDefault(value = 5) Pageable pageable, Authentication authentication) {
        model.addAttribute("employees",service.getAllEmployees(pageable, "dateOfBirth"));
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("url", "/birth");
        return "main-page";
    }

    //Отображение главной страницы отсортированной по дате приема на работу
    @GetMapping("/hiring")
    public String sortByHiring(Model model, @PageableDefault(value = 5) Pageable pageable, Authentication authentication) {
        model.addAttribute("employees",service.getAllEmployees(pageable, "dateOfHiring"));
        model.addAttribute("role", authentication.getAuthorities().toString());
        model.addAttribute("url", "/hiring");
        return "main-page";
    }

    //Отображение страницы сотрудника
    @GetMapping("/employee/{id}")
    public String getEmployeePage(@PathVariable("id") int id, Model model, Authentication authentication) {
        if(service.existsById(id)){
            model.addAttribute("role", authentication.getAuthorities().toString());
            service.getEmployee(id, model);
        }
        return "employee-page";
    }

    //Отображение странцы добавления нового сотрудника
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addEmployee")
    public String addEmployee(Model model) {
        if (!model.containsAttribute("form")) {
            model.addAttribute("form", new AddForm());
        }
        return "add-page";
    }

    //Добавление нового сотрудника
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addEmployee")
    public String addEmployee(@Valid @ModelAttribute AddForm form, BindingResult validationResult, RedirectAttributes attributes) {
        attributes.addFlashAttribute("form", form);
        System.out.println("form name" + form.getName());
        if (validationResult.hasErrors()) {
            attributes.addAttribute("errors", validationResult.getFieldErrors());
            return "redirect:/addEmployee";
        }
        if (service.dateHasErrors(form,attributes)) {
            return "redirect:/addEmployee";
        }
        service.addEmployee(form);
        return "redirect:/";
    }

    //Смена должности сотрудника
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/update/{id}")
    public String changePosition(@PathVariable("id") int id) {
        if(service.existsById(id)){
            service.updateEmployee(id);
        }
        return "redirect:/";
    }

    //Привязывание сотрудника к менеджеру
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/relation/{workerId}/{managerId}")
    public String relationEmployee(@PathVariable("workerId") int workerId, @PathVariable("managerId") int managerId) {
        service.changeRelation(workerId, managerId);
        return "redirect:/employee/" + workerId;
    }

    //Удаление сотрудника
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable("id") int id) {
        service.deleteEmployee(id);
        return "redirect:/";

    }
}
