package com.example.cana_be.model;


import javax.persistence.*;

@Entity
@Table
public class Category {
    @Id
    @GeneratedValue (strategy =  GenerationType.IDENTITY)
    private Long id;

    private String categoryName;

    public Category() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
