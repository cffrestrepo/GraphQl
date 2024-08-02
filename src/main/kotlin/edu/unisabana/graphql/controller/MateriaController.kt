package edu.unisabana.graphql.controller

import edu.unisabana.graphql.model.EstudianteDTO
import edu.unisabana.graphql.model.MateriaDTO
import edu.unisabana.graphql.service.EstudianteService
import edu.unisabana.graphql.service.MateriaService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class MateriaController(
    private val materiaService: MateriaService
) {

    @QueryMapping
    fun getAllMaterias(): List<MateriaDTO> =
        materiaService.findAll()


    @QueryMapping
    fun getMateriaById(@Argument id: Long): MateriaDTO =
        materiaService.get(id)
}
