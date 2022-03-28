package com.example.cana_be.controller;

import com.example.cana_be.dto.request.ChangePassword;
import com.example.cana_be.dto.response.ResponseMessage;
import com.example.cana_be.model.Orders;
import com.example.cana_be.model.User;
import com.example.cana_be.security.userprincal.UsersDetailService;
import com.example.cana_be.service.extend.IOrderService;
import com.example.cana_be.service.extend.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IUserService userService;

    @Autowired
    private UsersDetailService usersDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<User>> showList() {
        List<User> userList = userService.findAll();
        if (userList.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(userOptional.get(), HttpStatus.OK);
    }

    @PutMapping("/changePassword")
    public ResponseEntity<?> changPassword(@RequestBody ChangePassword changePassword) {
        User user = usersDetailService.getCurrentUser();
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if(bCryptPasswordEncoder.matches(changePassword.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(changePassword.getNewPassword()));
            userService.save(user);
            return new ResponseEntity<>(new ResponseMessage("Change password successful!"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(new ResponseMessage("Error!"), HttpStatus.OK);
        }

    }

    @PostMapping
    public ResponseEntity<?> createNewUser(@RequestBody User user) {
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        Optional<User> userOptional = userService.findById(user.getId());
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(new ResponseMessage("Không Có"), HttpStatus.NOT_FOUND);
        }
        userService.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        if (!userOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.remove(id);
        return new ResponseEntity<>(new ResponseMessage("Delete completed"), HttpStatus.OK);
    }
    @GetMapping("/findUserByUsernameOrEmail")
    public ResponseEntity<List<User>> getUserByUsernameOrEmail(@RequestParam() String userOrEmail){
        List<User> userList = userService.findByUsernameOrEmail(userOrEmail);
        if(userList.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(userList,HttpStatus.OK);
    }

    @GetMapping("/findCurrentOrder/{id}")
    public ResponseEntity<Orders> findCurrentOrder(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        Orders orders =  orderService.findOrdersByUserAndStatusId(user.get(),1);
        return new ResponseEntity<>(orders,HttpStatus.OK);
    }
}
