package com.machine_track_app.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "movement_types")
public class MovementType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movement_type")
    private Integer idMovementType;

    @Column(name = "type", nullable = false)
    private String type;

}
