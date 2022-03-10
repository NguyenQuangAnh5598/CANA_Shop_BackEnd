package com.example.cana_be.service.implement;

import com.example.cana_be.model.Role;
import com.example.cana_be.model.RoleName;
import com.example.cana_be.repository.IRoleRepo;
import com.example.cana_be.service.extend.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    IRoleRepo roleRepo;

    @Override
    public List<Role> findAll() {
        return roleRepo.findAll();
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleRepo.findById(id);
    }

    @Override
    public void save(Role role) {
        roleRepo.save(role);
    }

    @Override
    public void remove(Long id) {
        roleRepo.deleteById(id);
    }

    @Override
    public Optional<Role> findByRoleName(RoleName name) {
        return roleRepo.findByName(name);
    }
}
