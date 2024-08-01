package edu.unisabana.graphql.rest

import edu.unisabana.graphql.model.EstudianteDTO
import edu.unisabana.graphql.service.EstudianteService
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
    value = ["/api/estudiantes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class EstudianteResource(
    private val estudianteService: EstudianteService
) {

    @GetMapping
    fun getAllEstudiantes(): ResponseEntity<List<EstudianteDTO>> =
            ResponseEntity.ok(estudianteService.findAll())

    @GetMapping("/{id}")
    fun getEstudiante(@PathVariable(name = "id") id: Int): ResponseEntity<EstudianteDTO> =
            ResponseEntity.ok(estudianteService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createEstudiante(@RequestBody @Valid estudianteDTO: EstudianteDTO): ResponseEntity<Int> {
        val createdId = estudianteService.create(estudianteDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateEstudiante(@PathVariable(name = "id") id: Int, @RequestBody @Valid
            estudianteDTO: EstudianteDTO): ResponseEntity<Int> {
        estudianteService.update(id, estudianteDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteEstudiante(@PathVariable(name = "id") id: Int): ResponseEntity<Void> {
        estudianteService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
