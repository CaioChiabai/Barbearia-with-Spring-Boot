package com.caio.barbearia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.entities.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long>{

}
