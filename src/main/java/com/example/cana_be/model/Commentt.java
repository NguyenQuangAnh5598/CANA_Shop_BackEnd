package com.example.cana_be.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Commentt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;

    @ManyToOne
    private User user;

    private String textt;

    public Commentt() {
    }

    public Commentt(Long productId, User user, String textt) {
        this.productId = productId;
        this.user = user;
        this.textt = textt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getTextt() {
        return textt;
    }

    public void setTextt(String textt) {
        this.textt = textt;
    }
}
