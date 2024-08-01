package edu.unisabana.graphql.rest

import edu.unisabana.graphql.model.NotasDTO
import edu.unisabana.graphql.service.NotasService
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
    value = ["/api/notass"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class NotasResource(
    private val notasService: NotasService
) {

    @GetMapping
    fun getAllNotass(): ResponseEntity<List<NotasDTO>> = ResponseEntity.ok(notasService.findAll())

    @GetMapping("/{id}")
    fun getNotas(@PathVariable(name = "id") id: Long): ResponseEntity<NotasDTO> =
            ResponseEntity.ok(notasService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createNotas(@RequestBody @Valid notasDTO: NotasDTO): ResponseEntity<Long> {
        val createdId = notasService.create(notasDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateNotas(@PathVariable(name = "id") id: Long, @RequestBody @Valid notasDTO: NotasDTO):
            ResponseEntity<Long> {
        notasService.update(id, notasDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteNotas(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        notasService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
