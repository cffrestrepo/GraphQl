package edu.unisabana.graphql.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class EstudianteDTO {

    var id: Int? = null

    @NotNull
    @Size(max = 255)
    var nombre: String? = null

    @Size(max = 255)
    var apellido: String? = null

    @Size(max = 255)
    var profesion: String? = null

    var contactos: List<@Size(max = 255) String>? = null

    var materias: List<Long>? = null

    var hobbies: List<Int>? = null

}
