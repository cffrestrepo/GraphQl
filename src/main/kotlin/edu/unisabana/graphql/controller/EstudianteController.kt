package edu.unisabana.graphql.controller

import edu.unisabana.graphql.model.EstudianteDTO
import edu.unisabana.graphql.service.EstudianteService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class EstudianteController(
    private val estudianteService: EstudianteService
) {

    @QueryMapping
    fun getAllEstudiantes(): List<EstudianteDTO> =
        estudianteService.findAll()

    @QueryMapping
    fun estudiante(@Argument id: Int): EstudianteDTO =
        estudianteService.get(id)
}
