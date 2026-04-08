package com.tech.frist.dto.response;

import com.tech.frist.entity.Role;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {

    private Long id;
    private String name;
    private String email;
    private Role role;
}
