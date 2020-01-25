package me.kolganov.grannyshome.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "roles")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "role")
    private String role;

    @ToString.Exclude
    @ManyToMany(mappedBy = "roles")
    private List<AppUser> appUsers;
}
