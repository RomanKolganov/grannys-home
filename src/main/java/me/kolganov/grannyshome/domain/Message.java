package me.kolganov.grannyshome.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

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
    @Column(name = "creation_date")
    private Timestamp creationDate;

    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_to")
    private AppUser userTo;
    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "user_id_from")
    private AppUser userFrom;
    @ManyToOne(targetEntity = Dialog.class)
    @JoinColumn(name = "dialog_id")
    private Dialog dialog;
}
