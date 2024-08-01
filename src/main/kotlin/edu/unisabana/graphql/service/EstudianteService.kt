package edu.unisabana.graphql.service

import edu.unisabana.graphql.domain.Estudiante
import edu.unisabana.graphql.model.EstudianteDTO
import edu.unisabana.graphql.repos.EstudianteRepository
import edu.unisabana.graphql.repos.HobbyRepository
import edu.unisabana.graphql.repos.MateriaRepository
import edu.unisabana.graphql.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class EstudianteService(
    private val estudianteRepository: EstudianteRepository,
    private val materiaRepository: MateriaRepository,
    private val hobbyRepository: HobbyRepository
) {

    fun findAll(): List<EstudianteDTO> {
        val estudiantes = estudianteRepository.findAll(Sort.by("id"))
        return estudiantes.stream()
                .map { estudiante -> mapToDTO(estudiante, EstudianteDTO()) }
                .toList()
    }

    fun `get`(id: Int): EstudianteDTO = estudianteRepository.findById(id)
            .map { estudiante -> mapToDTO(estudiante, EstudianteDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(estudianteDTO: EstudianteDTO): Int {
        val estudiante = Estudiante()
        mapToEntity(estudianteDTO, estudiante)
        return estudianteRepository.save(estudiante).id!!
    }

    fun update(id: Int, estudianteDTO: EstudianteDTO) {
        val estudiante = estudianteRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(estudianteDTO, estudiante)
        estudianteRepository.save(estudiante)
    }

    fun delete(id: Int) {
        estudianteRepository.deleteById(id)
    }

    private fun mapToDTO(estudiante: Estudiante, estudianteDTO: EstudianteDTO): EstudianteDTO {
        estudianteDTO.id = estudiante.id
        estudianteDTO.nombre = estudiante.nombre
        estudianteDTO.apellido = estudiante.apellido
        estudianteDTO.profesion = estudiante.profesion
        estudianteDTO.contactos = estudiante.contactos
        estudianteDTO.materias = estudiante.materias?.stream()
                ?.map { materia -> materia.id!! }
                ?.toList()
        estudianteDTO.hobbies = estudiante.hobbies?.stream()
                ?.map { hobby -> hobby.id!! }
                ?.toList()
        return estudianteDTO
    }

    private fun mapToEntity(estudianteDTO: EstudianteDTO, estudiante: Estudiante): Estudiante {
        estudiante.nombre = estudianteDTO.nombre
        estudiante.apellido = estudianteDTO.apellido
        estudiante.profesion = estudianteDTO.profesion
        estudiante.contactos = estudianteDTO.contactos
        val materias = materiaRepository.findAllById(estudianteDTO.materias ?: emptyList())
        if (materias.size != (if (estudianteDTO.materias == null) 0 else
                estudianteDTO.materias!!.size)) {
            throw NotFoundException("one of materias not found")
        }
        estudiante.materias = materias.toMutableSet()
        val hobbies = hobbyRepository.findAllById(estudianteDTO.hobbies ?: emptyList())
        if (hobbies.size != (if (estudianteDTO.hobbies == null) 0 else
                estudianteDTO.hobbies!!.size)) {
            throw NotFoundException("one of hobbies not found")
        }
        estudiante.hobbies = hobbies.toMutableSet()
        return estudiante
    }

}
