package com.vodafone.ecommerce.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "category")
public class Category {

    //o categorie one to many cu un produs si one-to-many cu o alta categorie
    //o categorie sa aiba un parinte

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

//    @OneToMany(mappedBy = "category",
//            cascade = CascadeType.ALL)

//        @ManyToMany(fetch = FetchType.LAZY,
//            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
//                    CascadeType.DETACH, CascadeType.REFRESH})
//    @JoinTable(name="speaker_session", joinColumns = @JoinColumn(name="session_id"),
//            inverseJoinColumns = @JoinColumn(name="speaker_id"))

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns =@JoinColumn(name = "product_id") )
    private List<Product> products;
}

//@JoinTable(
//  name = "course_like",
//  joinColumns = @JoinColumn(name = "student_id"),
//  inverseJoinColumns = @JoinColumn(name = "course_id"))