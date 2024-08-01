package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Notas
import org.springframework.data.jpa.repository.JpaRepository


interface NotasRepository : JpaRepository<Notas, Long>
