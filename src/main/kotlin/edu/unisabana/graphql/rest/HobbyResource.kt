package edu.unisabana.graphql.rest

import edu.unisabana.graphql.model.HobbyDTO
import edu.unisabana.graphql.service.HobbyService
import io.swagger.v3.oas.annotations.responses.ApiResponse
import jakarta.validation.Valid
import java.lang.Void
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping(
    value = ["/api/hobbies"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class HobbyResource(
    private val hobbyService: HobbyService
) {

    @GetMapping
    fun getAllHobbies(): ResponseEntity<List<HobbyDTO>> = ResponseEntity.ok(hobbyService.findAll())

    @GetMapping("/{id}")
    fun getHobby(@PathVariable(name = "id") id: Int): ResponseEntity<HobbyDTO> =
            ResponseEntity.ok(hobbyService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createHobby(@RequestBody @Valid hobbyDTO: HobbyDTO): ResponseEntity<Int> {
        val createdId = hobbyService.create(hobbyDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateHobby(@PathVariable(name = "id") id: Int, @RequestBody @Valid hobbyDTO: HobbyDTO):
            ResponseEntity<Int> {
        hobbyService.update(id, hobbyDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteHobby(@PathVariable(name = "id") id: Int): ResponseEntity<Void> {
        hobbyService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
