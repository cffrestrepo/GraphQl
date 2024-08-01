package edu.unisabana.graphql.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.SequenceGenerator


@Entity
class Materia {

    @Id
    @Column(
        nullable = false,
        updatable = false
    )
    @SequenceGenerator(
        name = "primary_sequence",
        sequenceName = "primary_sequence",
        allocationSize = 1,
        initialValue = 10000
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "primary_sequence"
    )
    var id: Long? = null

    @Column(
        nullable = false,
        unique = true
    )
    var nombre: String? = null

    @Column(nullable = false)
    var profesor: String? = null

    @ManyToMany
    @JoinTable(
        name = "MateriaNota",
        joinColumns = [
            JoinColumn(name = "materiaId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "notasId")
        ]
    )
    var notas: MutableSet<Notas>? = null

    @ManyToMany(mappedBy = "materias")
    var docentes: MutableSet<Docente>? = null

    @ManyToMany
    @JoinTable(
        name = "MateriaSede",
        joinColumns = [
            JoinColumn(name = "materiaId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "sedeId")
        ]
    )
    var sede: MutableSet<Sede>? = null

}
