package edu.unisabana.graphql.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class DocenteDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var nombre: String? = null

    @NotNull
    @Size(max = 255)
    var apellido: String? = null

    @Size(max = 255)
    var profesion: String? = null

    var contactos: List<@Size(max = 255) String>? = null

    var materias: List<Long>? = null

}
