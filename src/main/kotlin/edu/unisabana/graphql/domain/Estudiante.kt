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
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes


@Entity
class Estudiante {

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
    var apellido: String? = null

    @Column
    var profesion: String? = null

    @Column(columnDefinition = "json")
    @JdbcTypeCode(SqlTypes.JSON)
    var contactos: List<String>? = null

    @ManyToMany
    @JoinTable(
        name = "EstudianteMateria",
        joinColumns = [
            JoinColumn(name = "estudianteId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "materiaId")
        ]
    )
    var materias: MutableSet<Materia>? = null

    @ManyToMany
    @JoinTable(
        name = "HobbyEstudiante",
        joinColumns = [
            JoinColumn(name = "estudianteId")
        ],
        inverseJoinColumns = [
            JoinColumn(name = "hobbyId")
        ]
    )
    var hobbies: MutableSet<Hobby>? = null

}
