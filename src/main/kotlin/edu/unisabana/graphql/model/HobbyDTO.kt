package edu.unisabana.graphql.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class HobbyDTO {

    var id: Int? = null

    @NotNull
    @Size(max = 255)
    var nombre: String? = null

    @Size(max = 255)
    var horasPorDias: String? = null

    var sede: List<Long>? = null

}
