package com.pinsoft.recipesite.Service;

import com.pinsoft.recipesite.Entity.Role;
import com.pinsoft.recipesite.Repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public List<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }
}