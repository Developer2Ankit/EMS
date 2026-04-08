package com.tech.frist.security;

import com.tech.frist.entity.Employee;
import com.tech.frist.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {

        Employee employee = repository.findByEmailAndDeletedFalse(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found"));

        return new User(
                employee.getEmail(),
                employee.getPassword(),
                Collections.singleton(() ->
                        employee.getRole().name())
        );
    }
}
