package me.kolganov.grannyshome.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @OneToMany(mappedBy = "user")
    private List<Order> createdOrders;
    @OneToMany(mappedBy = "userTo")
    private List<Comment> commentsTo;
    @OneToMany(mappedBy = "userFrom")
    private List<Comment> commentsFrom;

    @ManyToMany
    @JoinTable(
            name = "user_accepted_orders",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id"))
    private List<Order> acceptedOrders;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
}
