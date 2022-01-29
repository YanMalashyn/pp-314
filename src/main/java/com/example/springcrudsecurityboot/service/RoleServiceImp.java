package com.example.springcrudsecurityboot.service;


import com.example.springcrudsecurityboot.Repository.RoleRepository;
import com.example.springcrudsecurityboot.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class RoleServiceImp implements RoleService{

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImp(RoleRepository roleDao){
        this.roleRepository = roleDao;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole(Role role) {
        roleRepository.save(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }


    @Override
    public Role getRoleById(Long id) {
        return roleRepository.getById(id);
    }

}
