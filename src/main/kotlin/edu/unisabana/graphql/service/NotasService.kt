package edu.unisabana.graphql.service

import edu.unisabana.graphql.domain.Notas
import edu.unisabana.graphql.model.NotasDTO
import edu.unisabana.graphql.repos.MateriaRepository
import edu.unisabana.graphql.repos.NotasRepository
import edu.unisabana.graphql.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class NotasService(
    private val notasRepository: NotasRepository,
    private val materiaRepository: MateriaRepository
) {

    fun findAll(): List<NotasDTO> {
        val notases = notasRepository.findAll(Sort.by("id"))
        return notases.stream()
                .map { notas -> mapToDTO(notas, NotasDTO()) }
                .toList()
    }

    fun `get`(id: Long): NotasDTO = notasRepository.findById(id)
            .map { notas -> mapToDTO(notas, NotasDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(notasDTO: NotasDTO): Long {
        val notas = Notas()
        mapToEntity(notasDTO, notas)
        return notasRepository.save(notas).id!!
    }

    fun update(id: Long, notasDTO: NotasDTO) {
        val notas = notasRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(notasDTO, notas)
        notasRepository.save(notas)
    }

    fun delete(id: Long) {
        val notas = notasRepository.findById(id)
                .orElseThrow { NotFoundException() }
        // remove many-to-many relations at owning side
        materiaRepository.findAllByNotas(notas)
                .forEach { materia -> materia.notas!!.remove(notas) }
        notasRepository.delete(notas)
    }

    private fun mapToDTO(notas: Notas, notasDTO: NotasDTO): NotasDTO {
        notasDTO.id = notas.id
        notasDTO.actividad = notas.actividad
        notasDTO.nota = notas.nota
        return notasDTO
    }

    private fun mapToEntity(notasDTO: NotasDTO, notas: Notas): Notas {
        notas.actividad = notasDTO.actividad
        notas.nota = notasDTO.nota
        return notas
    }

}
