package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_validators")
public class UserValidator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_validator")
    private Long idValidator;

    @Column(name = "name", nullable = false)
    private String name;

    private LocalDateTime creation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner_customer")
    @JsonIgnore
    private OwnerCustomers ownerCustomers;


    @Column(name = "state", nullable = false)
    private Integer state;

}
