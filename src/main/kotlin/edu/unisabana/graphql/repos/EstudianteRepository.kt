package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Estudiante
import edu.unisabana.graphql.domain.Hobby
import edu.unisabana.graphql.domain.Materia
import org.springframework.data.jpa.repository.JpaRepository


interface EstudianteRepository : JpaRepository<Estudiante, Int> {

    fun findFirstByMaterias(materia: Materia): Estudiante?

    fun findFirstByHobbies(hobby: Hobby): Estudiante?

    fun findAllByMaterias(materia: Materia): List<Estudiante>

    fun findAllByHobbies(hobby: Hobby): List<Estudiante>

}
