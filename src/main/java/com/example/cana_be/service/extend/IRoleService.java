package com.example.cana_be.service.extend;

import com.example.cana_be.model.Role;
import com.example.cana_be.model.RoleName;
import com.example.cana_be.service.IGeneralService;

import java.util.Optional;

public interface IRoleService extends IGeneralService<Role> {
    Optional<Role> findByRoleName(RoleName name);
}
