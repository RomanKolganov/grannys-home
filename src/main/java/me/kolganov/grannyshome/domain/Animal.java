package me.kolganov.grannyshome.domain;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "animals")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column (name = "type")
    private String type;
    @Column (name = "birthday")
    private Date birthday;

    @ToString.Exclude
    @OneToOne(mappedBy = "animal")
    private Order order;

    @ToString.Exclude
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser user;
}
