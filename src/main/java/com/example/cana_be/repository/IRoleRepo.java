package com.example.cana_be.repository;

import com.example.cana_be.model.Role;
import com.example.cana_be.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepo extends JpaRepository<Role,Long> {
    Optional<Role> findByName(RoleName name);
}
