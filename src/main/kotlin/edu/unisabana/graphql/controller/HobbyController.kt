package edu.unisabana.graphql.controller

import edu.unisabana.graphql.model.HobbyDTO
import edu.unisabana.graphql.service.HobbyService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller

@Controller
class HobbyController(
    private val hobbyService: HobbyService
) {

    @QueryMapping
    fun getAllHobbies(): List<HobbyDTO> =
            hobbyService.findAll()

    @QueryMapping
    fun getHobby(@Argument id: Int): HobbyDTO =
            hobbyService.get(id)
}
