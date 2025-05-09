package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.machine_track_app.enums.IdentificationTypeEmployee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_employee")
    private Long idEmployee;
    private String identification;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "first_surname")
    private String firstSurname;
    @Column(name = "second_surname")
    private String secondSurname;
    private String email;
    private String phone;
    @Column(name = "owner_initial_detail")
    private String ownerInitialDetail;
    @ManyToOne
    @JoinColumn(name = "id_municipality")
    private Municipality municipality;
    private LocalDateTime creation;
    private LocalDateTime modification;
    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationTypeEmployee identificationType;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_owner")
    private Owner owner;
    @ManyToOne
    @JoinColumn(name = "id_state")
    private State state;
    @ManyToOne
    @JoinColumn(name = "id_position")
    private Position position;

}
