package edu.unisabana.graphql.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class MateriaDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    @MateriaNombreUnique
    var nombre: String? = null

    @NotNull
    @Size(max = 255)
    var profesor: String? = null

    var notas: List<NotasDTO>? = emptyList()

    var sede: List<SedeDTO>? = emptyList()

}
