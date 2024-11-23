package com.caio.barbearia.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.Status;
import java.util.List;


@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{
    Optional<Status> findByDescricao(String descricao);
}
