package com.tech.frist.service;

import com.tech.frist.dto.response.PageResponse;
import com.tech.frist.dto.response.EmployeeResponse;

public interface EmployeeService {

    PageResponse<EmployeeResponse> getAllEmployees(int page, int size);
}
