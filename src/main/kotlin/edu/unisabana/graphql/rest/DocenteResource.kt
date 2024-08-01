package edu.unisabana.graphql.rest

import edu.unisabana.graphql.model.DocenteDTO
import edu.unisabana.graphql.service.DocenteService
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
    value = ["/api/docentes"],
    produces = [MediaType.APPLICATION_JSON_VALUE]
)
class DocenteResource(
    private val docenteService: DocenteService
) {

    @GetMapping
    fun getAllDocentes(): ResponseEntity<List<DocenteDTO>> =
            ResponseEntity.ok(docenteService.findAll())

    @GetMapping("/{id}")
    fun getDocente(@PathVariable(name = "id") id: Long): ResponseEntity<DocenteDTO> =
            ResponseEntity.ok(docenteService.get(id))

    @PostMapping
    @ApiResponse(responseCode = "201")
    fun createDocente(@RequestBody @Valid docenteDTO: DocenteDTO): ResponseEntity<Long> {
        val createdId = docenteService.create(docenteDTO)
        return ResponseEntity(createdId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateDocente(@PathVariable(name = "id") id: Long, @RequestBody @Valid
            docenteDTO: DocenteDTO): ResponseEntity<Long> {
        docenteService.update(id, docenteDTO)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/{id}")
    @ApiResponse(responseCode = "204")
    fun deleteDocente(@PathVariable(name = "id") id: Long): ResponseEntity<Void> {
        docenteService.delete(id)
        return ResponseEntity.noContent().build()
    }

}
