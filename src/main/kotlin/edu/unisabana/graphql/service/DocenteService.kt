package edu.unisabana.graphql.service

import edu.unisabana.graphql.domain.Docente
import edu.unisabana.graphql.model.DocenteDTO
import edu.unisabana.graphql.repos.DocenteRepository
import edu.unisabana.graphql.repos.MateriaRepository
import edu.unisabana.graphql.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class DocenteService(
    private val docenteRepository: DocenteRepository,
    private val materiaRepository: MateriaRepository
) {

    fun findAll(): List<DocenteDTO> {
        val docentes = docenteRepository.findAll(Sort.by("id"))
        return docentes.stream()
                .map { docente -> mapToDTO(docente, DocenteDTO()) }
                .toList()
    }

    fun `get`(id: Long): DocenteDTO = docenteRepository.findById(id)
            .map { docente -> mapToDTO(docente, DocenteDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(docenteDTO: DocenteDTO): Long {
        val docente = Docente()
        mapToEntity(docenteDTO, docente)
        return docenteRepository.save(docente).id!!
    }

    fun update(id: Long, docenteDTO: DocenteDTO) {
        val docente = docenteRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(docenteDTO, docente)
        docenteRepository.save(docente)
    }

    fun delete(id: Long) {
        docenteRepository.deleteById(id)
    }

    private fun mapToDTO(docente: Docente, docenteDTO: DocenteDTO): DocenteDTO {
        docenteDTO.id = docente.id
        docenteDTO.nombre = docente.nombre
        docenteDTO.apellido = docente.apellido
        docenteDTO.profesion = docente.profesion
        docenteDTO.contactos = docente.contactos
        docenteDTO.materias = docente.materias?.stream()
                ?.map { materia -> materia.id!! }
                ?.toList()
        return docenteDTO
    }

    private fun mapToEntity(docenteDTO: DocenteDTO, docente: Docente): Docente {
        docente.nombre = docenteDTO.nombre
        docente.apellido = docenteDTO.apellido
        docente.profesion = docenteDTO.profesion
        docente.contactos = docenteDTO.contactos
        val materias = materiaRepository.findAllById(docenteDTO.materias ?: emptyList())
        if (materias.size != (if (docenteDTO.materias == null) 0 else docenteDTO.materias!!.size)) {
            throw NotFoundException("one of materias not found")
        }
        docente.materias = materias.toMutableSet()
        return docente
    }

}
