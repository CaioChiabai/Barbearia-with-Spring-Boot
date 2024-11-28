package com.caio.barbearia.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caio.barbearia.dto.ProcedimentoView;
import com.caio.barbearia.entities.Procedimento;

@Repository
public interface ProcedimentoRepository extends JpaRepository<Procedimento,Long>{

    List<ProcedimentoView> findAllProjectedBy();
}