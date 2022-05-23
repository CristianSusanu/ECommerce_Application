package com.vodafone.ecommerce.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

//    @ManyToOne(cascade = CascadeType.PERSIST)
    @ManyToMany(mappedBy = "products")
    private Set<Category> category = new HashSet<>();

//        @ManyToMany(mappedBy = "speakers")
//    private Set<Session> sessions = new HashSet<>();

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Long price;
}
