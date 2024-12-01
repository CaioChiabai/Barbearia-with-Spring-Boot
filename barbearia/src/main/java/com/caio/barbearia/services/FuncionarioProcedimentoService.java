package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.FuncionarioProcedimentoRequest;
import com.caio.barbearia.dto.response.FuncionarioProcedimentoResponse;
import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.FuncionarioProcedimentoMapper;
import com.caio.barbearia.repositories.FuncionarioProcedimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class FuncionarioProcedimentoService {

    private Logger logger = Logger.getLogger(FuncionarioProcedimentoService.class.getName());

    @Autowired
    private FuncionarioProcedimentoRepository repository;
    
    @Autowired
    private  FuncionarioProcedimentoMapper mapper;

    public List<FuncionarioProcedimentoResponse> findAll() {
        List<FuncionarioProcedimento> entities = repository.findAll();
        return mapper.toFuncionarioProcedimentoResponseList(entities);
    }

    public FuncionarioProcedimentoResponse findById(Long id) {
        FuncionarioProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id));
        return mapper.toFuncionarioProcedimentoResponse(entity);
    }

    public FuncionarioProcedimentoResponse create(FuncionarioProcedimentoRequest request) {
        FuncionarioProcedimento entity = mapper.toFuncionarioProcedimento(request);
        FuncionarioProcedimento savedEntity = repository.save(entity);
        return mapper.toFuncionarioProcedimentoResponse(savedEntity);
    }

    public FuncionarioProcedimentoResponse update(Long id, FuncionarioProcedimentoRequest request) {
        FuncionarioProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id));
        mapper.updateFuncionarioProcedimentoFromRequest(request, entity);
        FuncionarioProcedimento updatedEntity = repository.save(entity);
        return mapper.toFuncionarioProcedimentoResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
