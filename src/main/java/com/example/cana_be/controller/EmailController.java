package com.example.cana_be.controller;

import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.User;
import com.example.cana_be.service.extend.IUserService;
import com.example.cana_be.service.implement.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailServiceImpl emailService;

    @Autowired
    IUserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;


//    @GetMapping
//    public

    @PostMapping
    public ResponseEntity<?> getForgotPassword(@RequestBody String email) {
        String password = String.valueOf((int) (((Math.random()) * ((999999 - 100000)) + 100000)));
        Optional<User> userOptional = userService.findUserByEmail(email);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("No Email"), HttpStatus.OK);
        } else {
            String subject = "Email lấy lại mật khẩu";
            String body = "Mật khẩu mới của bạn là: " + password + " .Xin hãy đăng nhập lại: http://localhost:4200/login";
            userOptional.get().setPassword(passwordEncoder.encode(password));
            userService.save(userOptional.get());
            emailService.sendEmail(email, subject, body);
            return new ResponseEntity<>(new ResponseMessage("OK"), HttpStatus.OK);
        }

    }

}
