package edu.unisabana.graphql.repos

import edu.unisabana.graphql.domain.Hobby
import org.springframework.data.jpa.repository.JpaRepository


interface HobbyRepository : JpaRepository<Hobby, Int>
