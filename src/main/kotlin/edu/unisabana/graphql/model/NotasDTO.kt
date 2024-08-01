package edu.unisabana.graphql.model

import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size


class NotasDTO {

    var id: Long? = null

    @NotNull
    @Size(max = 255)
    var actividad: String? = null

    @NotNull
    var nota: Int? = null

}
