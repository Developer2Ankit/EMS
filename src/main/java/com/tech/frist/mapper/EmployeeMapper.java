package com.tech.frist.mapper;

import com.tech.frist.dto.response.EmployeeResponse;
import com.tech.frist.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeResponse toResponse(Employee employee);
}
