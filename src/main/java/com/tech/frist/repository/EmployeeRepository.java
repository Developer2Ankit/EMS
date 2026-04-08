package com.tech.frist.repository;

import com.tech.frist.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmailAndDeletedFalse(String email);

    Page<Employee> findByDeletedFalse(Pageable pageable);
}
