package me.kolganov.grannyshome.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;

    @OneToOne
    @JoinColumn(name = "animal_id", referencedColumnName = "id")
    private Animal animal;

    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ToString.Exclude
    @OneToMany(mappedBy = "order")
    private List<AcceptedOrder> acceptedOrders;
}
