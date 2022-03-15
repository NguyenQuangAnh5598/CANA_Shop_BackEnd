package com.example.cana_be.service.extend;

import com.example.cana_be.model.User;
import com.example.cana_be.service.IGeneralService;

import java.util.List;
import java.util.Optional;

public interface IUserService extends IGeneralService<User> {
Optional<User> findUserByUserName(String userName);

Boolean existsByUserName(String userName);

Boolean existsByEmail(String email);

List<User> findByUsernameOrEmail(String userOrEmail);
}
