package com.machine_track_app.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "user")
    private String userName;

    private String password;

    private LocalDateTime creation;

    private LocalDateTime modification;

    @ManyToOne
    @JoinColumn(name = "id_employee")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "id_rol")
    private UserRole role;

    @ManyToOne
    @JoinColumn(name = "id_state")
    private State state;

}
