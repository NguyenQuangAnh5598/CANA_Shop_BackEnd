package com.example.cana_be.model;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    private String avatar;

    @NotBlank
    @Email
    private String email;

    private String phone;

    private String address;

    private LocalDate dob;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Orders> ordersList;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    Set<Role> roles = new HashSet<>();

    public User(){}

    public User(Long id, String username, String password, String name, String avatar, String email, String phone, String address, LocalDate dob, List<Orders> ordersList, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
        this.ordersList = ordersList;
        this.roles = roles;
    }

    public User(Long id, String name, String avatar, String email, String phone, String address, LocalDate dob) {
        this.id = id;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.dob = dob;
    }

    public User(String username, String password, String name, String avatar, String email, String phone, String address) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public User(String username, String password, String name, String avatar, String email) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String user_name) {
        this.username = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public List<Orders> getOrderList() {
        return ordersList;
    }

    public void setOrderList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
