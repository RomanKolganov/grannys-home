package me.kolganov.grannyshome.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_accepted_orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AcceptedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "accepted_user_id")
    private AppUser user;
    @OneToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
    private Order order;
}
