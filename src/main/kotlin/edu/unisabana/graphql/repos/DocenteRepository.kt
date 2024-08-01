package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Docente
import edu.unisabana.graphql.domain.Materia
import org.springframework.data.jpa.repository.JpaRepository


interface DocenteRepository : JpaRepository<Docente, Long> {

    fun findFirstByMaterias(materia: Materia): Docente?

    fun findAllByMaterias(materia: Materia): List<Docente>

}
