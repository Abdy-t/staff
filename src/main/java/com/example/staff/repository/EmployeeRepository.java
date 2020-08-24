package com.example.staff.repository;

import com.example.staff.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee getById(int id);

    List<Employee> findAllByPosition(String position);

    Page<Employee> findAll(Pageable pageable);

    @Query("SELECT e FROM Employee as e WHERE e.manager_id = :id")
    List<Employee> findEmployeeByManager_id(Integer id);



}
