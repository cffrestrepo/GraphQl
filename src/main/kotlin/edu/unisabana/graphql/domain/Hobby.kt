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
class Hobby {

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
    var id: Int? = null

    @Column(nullable = false)
    var nombre: String? = null

    @Column
    var horasPorDias: String? = null

    @ManyToMany(mappedBy = "hobbies")
    var estudiantes: MutableSet<Estudiante>? = null

    @ManyToMany
    @JoinTable(
        name = "HobbySede",
        joinColumns = [
            JoinColumn(name = "hobbyId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "sedeId")
        ]
    )
    var sede: MutableSet<Sede>? = null

}
