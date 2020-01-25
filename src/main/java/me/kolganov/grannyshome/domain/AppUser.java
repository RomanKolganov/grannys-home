package me.kolganov.grannyshome.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "login", unique = true, nullable = false)
    private String login;
    @Column(name = "password")
    private String password;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Order> createdOrders;
    @ToString.Exclude
    @OneToMany(mappedBy = "userTo")
    private List<Comment> commentsTo;
    @ToString.Exclude
    @OneToMany(mappedBy = "userFrom")
    private List<Comment> commentsFrom;
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Animal> animals;
    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<AcceptedOrder> acceptedOrders;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
