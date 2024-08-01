package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Sede
import org.springframework.data.jpa.repository.JpaRepository


interface SedeRepository : JpaRepository<Sede, Long>
