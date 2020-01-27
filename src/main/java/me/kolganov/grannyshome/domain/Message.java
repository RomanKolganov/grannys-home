package me.kolganov.grannyshome.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "text")
    private String text;

    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_to")
    private AppUser userTo;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_from")
    private AppUser userFrom;
}
