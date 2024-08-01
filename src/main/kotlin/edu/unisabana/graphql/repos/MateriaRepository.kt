package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Materia
import edu.unisabana.graphql.domain.Notas
import org.springframework.data.jpa.repository.JpaRepository


interface MateriaRepository : JpaRepository<Materia, Long> {

    fun findFirstByNotas(notas: Notas): Materia?

    fun findAllByNotas(notas: Notas): List<Materia>

    fun existsByNombreIgnoreCase(nombre: String?): Boolean

}
