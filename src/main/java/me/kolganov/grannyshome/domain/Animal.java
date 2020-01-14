package me.kolganov.grannyshome.domain;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "animals")
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "quantity")
    private Integer quantity;

    @OneToOne(mappedBy = "animal")
    private Order order;
}
