package com.example.staff.configuration;

import com.example.staff.model.Employee;
import com.example.staff.model.User;
import com.example.staff.repository.EmployeeRepository;
import com.example.staff.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;

@Configuration
public class PreloadEmployees {
    private final PasswordEncoder encoder;

    public PreloadEmployees(PasswordEncoder encoder) {
        this.encoder = encoder;
    }

    @Bean
    CommandLineRunner initializationDB(EmployeeRepository employeeRepository, UserRepository userRepository) {
        return(args) -> {
            employeeRepository.deleteAll();
            userRepository.deleteAll();

            User admin = User.builder()
                    .email("admin@mail.ru")
                    .password(encoder.encode("123"))
                    .role("ADMIN")
                    .build();
            userRepository.save(admin);

            User user = User.builder()
                    .email("user@mail.ru")
                    .password(encoder.encode("123"))
                    .role("USER")
                    .build();
            userRepository.save(user);

            Employee manager = Employee.builder()
                    .surname("Aibekov")
                    .name("Aibek")
                    .patronymic("Aibekovich")
                    .dateOfBirth(LocalDate.of(1980,12,2))
                    .dateOfHiring(LocalDate.of(2000,9,12))
                    .position("MANAGER")
                    .build();
            employeeRepository.save(manager);

            Employee manager2 = Employee.builder()
                    .surname("Tashmatov")
                    .name("Nurlan")
                    .patronymic("Tashmatovich")
                    .dateOfBirth(LocalDate.of(1992,5,30))
                    .dateOfHiring(LocalDate.of(2005,3,24))
                    .position("MANAGER")
                    .build();
            employeeRepository.save(manager2);

            Employee manager3 = Employee.builder()
                    .surname("Nurlanov")
                    .name("Kairat")
                    .patronymic("Sadyrbekovich")
                    .dateOfBirth(LocalDate.of(1997,3,16))
                    .dateOfHiring(LocalDate.of(2007,8,21))
                    .position("MANAGER")
                    .build();
            employeeRepository.save(manager3);

            Employee employee = Employee.builder()
                    .surname("Azamatov")
                    .name("Azamat")
                    .patronymic("Azamatovich")
                    .dateOfBirth(LocalDate.of(1990,1,1))
                    .dateOfHiring(LocalDate.of(2010,1,1))
                    .manager_id(manager.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee);

            Employee employee2 = Employee.builder()
                    .surname("Shermatov")
                    .name("Tilek")
                    .patronymic("Azamatovich")
                    .dateOfBirth(LocalDate.of(1987,2,8))
                    .dateOfHiring(LocalDate.of(2016,8,31))
                    .manager_id(manager.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee2);

            Employee employee3 = Employee.builder()
                    .surname("Bekturov")
                    .name("Bektur")
                    .patronymic("Bekturovich")
                    .dateOfBirth(LocalDate.of(1995,2,2))
                    .dateOfHiring(LocalDate.of(2010,4,12))
                    .manager_id(manager.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee3);

            Employee employee4 = Employee.builder()
                    .surname("Sadyrbekov")
                    .name("Alybek")
                    .patronymic("Amanbaevich")
                    .dateOfBirth(LocalDate.of(1991,11,22))
                    .dateOfHiring(LocalDate.of(2019,3,14))
                    .manager_id(manager2.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee4);

            Employee employee5 = Employee.builder()
                    .surname("Kadyrbekov")
                    .name("Sanjhar")
                    .patronymic("Altynbaevich")
                    .dateOfBirth(LocalDate.of(1985,6,13))
                    .dateOfHiring(LocalDate.of(2017,7,21))
                    .manager_id(manager2.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee5);

            Employee employee6 = Employee.builder()
                    .surname("Timurov")
                    .name("Aziz")
                    .patronymic("Timurovich")
                    .dateOfBirth(LocalDate.of(2001,9,30))
                    .dateOfHiring(LocalDate.of(2020,4,17))
                    .manager_id(manager3.getId())
                    .position("WORKER")
                    .build();
            employeeRepository.save(employee6);

        };
    }
}