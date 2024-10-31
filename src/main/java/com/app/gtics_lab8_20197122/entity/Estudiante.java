package com.app.gtics_lab8_20197122.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "Estudiante")
public class Estudiante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idEstudiante", nullable = false)
    private Integer id;

    @Size(max = 8)
    @NotNull
    @Column(name = "dni", nullable = false, length = 8)
    private String dni;

    @Size(max = 45)
    @NotNull
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @ColumnDefault("0.00")
    @Column(name = "gpa", precision = 10, scale = 2)
    private BigDecimal gpa;

    @Size(max = 45)
    @NotNull
    @Column(name = "facultad", nullable = false, length = 45)
    private String facultad;

    @ColumnDefault("0.00")
    @Column(name = "creditosCom", precision = 10, scale = 2)
    private BigDecimal creditosCom;

}
