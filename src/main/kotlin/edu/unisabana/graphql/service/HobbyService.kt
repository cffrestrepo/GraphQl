package edu.unisabana.graphql.service

import edu.unisabana.graphql.domain.Hobby
import edu.unisabana.graphql.model.HobbyDTO
import edu.unisabana.graphql.repos.EstudianteRepository
import edu.unisabana.graphql.repos.HobbyRepository
import edu.unisabana.graphql.repos.SedeRepository
import edu.unisabana.graphql.util.NotFoundException
import jakarta.transaction.Transactional
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service


@Service
@Transactional
class HobbyService(
    private val hobbyRepository: HobbyRepository,
    private val sedeRepository: SedeRepository,
    private val estudianteRepository: EstudianteRepository
) {

    fun findAll(): List<HobbyDTO> {
        val hobbies = hobbyRepository.findAll(Sort.by("id"))
        return hobbies.stream()
                .map { hobby -> mapToDTO(hobby, HobbyDTO()) }
                .toList()
    }

    fun `get`(id: Int): HobbyDTO = hobbyRepository.findById(id)
            .map { hobby -> mapToDTO(hobby, HobbyDTO()) }
            .orElseThrow { NotFoundException() }

    fun create(hobbyDTO: HobbyDTO): Int {
        val hobby = Hobby()
        mapToEntity(hobbyDTO, hobby)
        return hobbyRepository.save(hobby).id!!
    }

    fun update(id: Int, hobbyDTO: HobbyDTO) {
        val hobby = hobbyRepository.findById(id)
                .orElseThrow { NotFoundException() }
        mapToEntity(hobbyDTO, hobby)
        hobbyRepository.save(hobby)
    }

    fun delete(id: Int) {
        val hobby = hobbyRepository.findById(id)
                .orElseThrow { NotFoundException() }
        // remove many-to-many relations at owning side
        estudianteRepository.findAllByHobbies(hobby)
                .forEach { estudiante -> estudiante.hobbies!!.remove(hobby) }
        hobbyRepository.delete(hobby)
    }

    private fun mapToDTO(hobby: Hobby, hobbyDTO: HobbyDTO): HobbyDTO {
        hobbyDTO.id = hobby.id
        hobbyDTO.nombre = hobby.nombre
        hobbyDTO.horasPorDias = hobby.horasPorDias
        hobbyDTO.sede = hobby.sede?.stream()
                ?.map { sede -> sede.id!! }
                ?.toList()
        return hobbyDTO
    }

    private fun mapToEntity(hobbyDTO: HobbyDTO, hobby: Hobby): Hobby {
        hobby.nombre = hobbyDTO.nombre
        hobby.horasPorDias = hobbyDTO.horasPorDias
        val sede = sedeRepository.findAllById(hobbyDTO.sede ?: emptyList())
        if (sede.size != (if (hobbyDTO.sede == null) 0 else hobbyDTO.sede!!.size)) {
            throw NotFoundException("one of sede not found")
        }
        hobby.sede = sede.toMutableSet()
        return hobby
    }

}
