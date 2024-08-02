package edu.unisabana.graphql.service

import edu.unisabana.graphql.domain.Materia
import edu.unisabana.graphql.model.MateriaDTO
import edu.unisabana.graphql.model.NotasDTO
import edu.unisabana.graphql.model.SedeDTO
import edu.unisabana.graphql.repos.DocenteRepository
import edu.unisabana.graphql.repos.EstudianteRepository
import edu.unisabana.graphql.repos.MateriaRepository
import edu.unisabana.graphql.repos.NotasRepository
import edu.unisabana.graphql.repos.SedeRepository
import edu.unisabana.graphql.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class MateriaService(
    private val materiaRepository: MateriaRepository,
    private val notasRepository: NotasRepository,
    private val sedeRepository: SedeRepository,
    private val estudianteRepository: EstudianteRepository,
    private val docenteRepository: DocenteRepository
) {

    fun findAll(): List<MateriaDTO> {
        val materias = materiaRepository.findAll(Sort.by("id"))
        return materias.stream()
                .map { materia -> mapToDTO(materia, MateriaDTO()) }
                .toList()
    }

    fun `get`(id: Long): MateriaDTO = materiaRepository.findById(id)
            .map { materia -> mapToDTO(materia, MateriaDTO()) }
            .orElseThrow { NotFoundException("Materia with ID $id not found") }


    fun create(materiaDTO: MateriaDTO): Long {
        val materia = Materia()
        mapToEntity(materiaDTO, materia)
        return materiaRepository.save(materia).id!!
    }

    fun update(id: Long, materiaDTO: MateriaDTO) {
        val materia = materiaRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(materiaDTO, materia)
        materiaRepository.save(materia)
    }

    fun delete(id: Long) {
        val materia = materiaRepository.findById(id)
                .orElseThrow { NotFoundException() }
        // remove many-to-many relations at owning side
        estudianteRepository.findAllByMaterias(materia)
                .forEach { estudiante -> estudiante.materias!!.remove(materia) }
        docenteRepository.findAllByMaterias(materia)
                .forEach { docente -> docente.materias!!.remove(materia) }
        materiaRepository.delete(materia)
    }

    private fun mapToDTO(materia: Materia, materiaDTO: MateriaDTO): MateriaDTO {
        materiaDTO.id = materia.id
        materiaDTO.nombre = materia.nombre
        materiaDTO.profesor = materia.profesor
        materiaDTO.notas = materia.notas?.map { nota ->
            NotasDTO(id = nota.id, nota = nota.nota, actividad = nota.actividad )}
        materiaDTO.sede =  materia.sede?.map { sede ->
            SedeDTO(id = sede.id,  locacion= sede.locacion)}
        return materiaDTO
    }

    private fun mapToEntity(materiaDTO: MateriaDTO, materia: Materia): Materia {
        materia.nombre = materiaDTO.nombre
        materia.profesor = materiaDTO.profesor
        val notas = materiaDTO.notas?.map { nota ->
            notasRepository.findById(nota.id!!).orElseThrow { NotFoundException("Nota with id $nota not found") }
        }?.toMutableSet() ?: mutableSetOf()

        materia.notas = notas
        val sede = materiaDTO.sede?.map { sede ->
            sedeRepository.findById(sede.id!!).orElseThrow { NotFoundException("Sede with id $sede not found") }
        }?.toMutableSet() ?: mutableSetOf()

        materia.sede = sede
        return materia
    }

    fun nombreExists(nombre: String?): Boolean = materiaRepository.existsByNombreIgnoreCase(nombre)

}
