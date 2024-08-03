package edu.unisabana.graphql.controller

import edu.unisabana.graphql.model.DocenteDTO
import edu.unisabana.graphql.service.DocenteService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class DocenteController (
    private val docenteService: DocenteService
) {

    @QueryMapping
    fun getAllDocentes(): List<DocenteDTO> =
        docenteService.findAll()

    @QueryMapping
    fun getDocente(@Argument id: Long): DocenteDTO =
        docenteService.get(id)
}