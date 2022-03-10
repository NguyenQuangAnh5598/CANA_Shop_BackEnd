package com.example.cana_be.service.implement;

import com.example.cana_be.model.User;
import com.example.cana_be.repository.IUserRepo;
import com.example.cana_be.service.extend.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    IUserRepo userRepo;

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepo.findById(id);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void remove(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public Optional<User> findUserByUserName(String userName) {
        return userRepo.findUserByUsername(userName);
    }

    @Override
    public Boolean existsByUserName(String userName) {
        return userRepo.existsByUsername(userName);
    }

    @Override
    public Boolean existsByEmail(String email) {
        return userRepo.existsByEmail(email);
    }
}
