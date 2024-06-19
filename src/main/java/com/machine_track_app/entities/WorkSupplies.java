package com.machine_track_app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "work_supplies")
public class WorkSupplies {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_work_supplies")
    private Long idWorkSupplies;

    @Column(name = "date", nullable = false)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_work_detail")
    @JsonIgnore
    private WorkDetails workDetails;

    @Column(name = "acpm_golls")
    private Integer acpmGolls;
}
