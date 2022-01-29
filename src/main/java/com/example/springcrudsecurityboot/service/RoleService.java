package com.example.springcrudsecurityboot.service;



import com.example.springcrudsecurityboot.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();

    void saveRole(Role role);

    void deleteRoleById(Long id);

    Role getRoleById(Long id);

}
