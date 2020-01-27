package me.kolganov.grannyshome.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.util.List;

@Entity
@Table(name = "dialogs")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dialog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_to")
    private AppUser userTo;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_from")
    private AppUser userFrom;

    @ToString.Exclude
    @OneToMany(mappedBy = "dialog")
    private List<Message> messages;
}
