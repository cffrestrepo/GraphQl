package edu.unisabana.graphql.rest

import edu.unisabana.graphql.model.MateriaDTO
import edu.unisabana.graphql.service.MateriaService
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
    value = ["/api/materias"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class MateriaResource(
    private val materiaService: MateriaService
) {

    @GetMapping
    fun getAllMaterias(): ResponseEntity<List<MateriaDTO>> =
            ResponseEntity.ok(materiaService.findAll())

    @GetMapping("/{id}")
    fun getMateria(@PathVariable(name = "id") id: Long): ResponseEntity<MateriaDTO> =
            ResponseEntity.ok(materiaService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createMateria(@RequestBody @Valid materiaDTO: MateriaDTO): ResponseEntity<Long> {
        val createdId = materiaService.create(materiaDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateMateria(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            materiaDTO: MateriaDTO): ResponseEntity<Long> {
        materiaService.update(id, materiaDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteMateria(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        materiaService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
