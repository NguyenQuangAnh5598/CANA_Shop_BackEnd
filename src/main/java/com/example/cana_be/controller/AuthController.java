package com.example.cana_be.controller;

import com.example.cana_be.dto.request.SignInForm;
import com.example.cana_be.dto.request.SignUpForm;
import com.example.cana_be.dto.response.JwtResponse;
import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Role;
import com.example.cana_be.model.RoleName;
import com.example.cana_be.model.User;
import com.example.cana_be.security.jwt.JwtProvider;
import com.example.cana_be.security.userprincal.UserPrinciple;
import com.example.cana_be.service.extend.IRoleService;
import com.example.cana_be.service.extend.IStatusService;
import com.example.cana_be.service.extend.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    IUserService userService;

    @Autowired
    IRoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    IStatusService statusService;

    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm) {
        if (userService.existsByUserName(signUpForm.getUserName())) {
            return new ResponseEntity<>(new ResponseMessage("UserName was exists!"), HttpStatus.OK);
        }
        if (userService.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Email was exists!"), HttpStatus.OK);
        }
        if (signUpForm.getAvatar() == null || signUpForm.getAvatar().trim().isEmpty()) {
            signUpForm.setAvatar("https://firebasestorage.googleapis.com/v0/b/chinhbeo-18d3b.appspot.com/o/avatar.png?alt=media&token=3511cf81-8df2-4483-82a8-17becfd03211");
        }

        User user = new User(signUpForm.getUserName(),passwordEncoder.encode(signUpForm.getPassword()) , signUpForm.getName(), signUpForm.getAvatar(), signUpForm.getEmail(),signUpForm.getPhone(),signUpForm.getAddress());
        Set<Role> roles = new HashSet<>();
        Role userRole = roleService.findByRoleName(RoleName.CUSTOMER).orElseThrow(() -> new RuntimeException("Role not found"));
        roles.add(userRole);
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity<>(new ResponseMessage("Signup Success!"), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SignInForm signInForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInForm.getUserName(),signInForm.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        User userLogin = userService.findUserByUserName(userPrinciple.getUsername()).get();

        for (Role r : userLogin.getRoles()) {

            if (r.getName().equals(RoleName.ADMIN)) {
                String token = jwtProvider.createToken(authentication);
                return ResponseEntity.ok(new JwtResponse(userPrinciple.getId(), token, userPrinciple.getName(), userPrinciple.getAvatar(),userPrinciple.getAuthorities()));
            } else if (r.getName().equals(RoleName.CUSTOMER)) {
                String token = jwtProvider.createToken(authentication);
                return ResponseEntity.ok(new JwtResponse(userPrinciple.getId(), token, userPrinciple.getName(),userPrinciple.getAvatar(), userPrinciple.getAuthorities()));

            }

        }
        return new ResponseEntity<>(new ResponseMessage("Your account has been error"), HttpStatus.OK);
    }
}
