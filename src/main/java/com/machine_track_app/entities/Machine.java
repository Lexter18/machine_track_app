package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "machines")
public class Machine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_machine")
    private Long idMachine;

    @Column(name = "machine_name", nullable = false)
    private String machineName;

    @Column(name = "transit_record", nullable = false)
    private String transitRecord;

    @ManyToOne
    @JoinColumn(name = "id_brand", nullable = false)
    private MachineBrand brand;

    @Column(name = "line", nullable = false)
    private String line;

    @Column(name = "model", nullable = false)
    private int model;

    @ManyToOne
    @JoinColumn(name = "id_type")
    private MachineType type;

    @Column(name = "class", nullable = false)
    private String machineClass;

    @Column(name = "serial_number", nullable = false)
    private String serialNumber;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "engine_number", nullable = false)
    private String engineNumber;

    @ManyToOne
    @JoinColumn(name = "id_movement_type")
    private MovementType movementType;

    @ManyToOne
    @JoinColumn(name = "id_gps")
    private MachineGPS machineGPS;

    @Column(name = "weight", nullable = false)
    private int weight;

    @Column(name = "measures", nullable = false)
    private String measures;

    @Column(name = "measure_bucket")
    private String measureBucket;

    @Column(name = "owner", nullable = false)
    private String owner;

    @Column(name = "import_number")
    private String importNumber;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_owner")
    private Owner machineOwner;
}
