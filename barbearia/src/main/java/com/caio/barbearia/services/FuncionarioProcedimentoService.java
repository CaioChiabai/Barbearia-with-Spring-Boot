package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.FuncionarioProcedimento.FuncionarioProcedimentoMinRequest;
import com.caio.barbearia.dto.request.FuncionarioProcedimento.FuncionarioProcedimentoRequest;
import com.caio.barbearia.dto.response.FuncionarioProcedimento.FuncionarioProcedimentoResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.FuncionarioProcedimento;
import com.caio.barbearia.entities.Procedimento;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.FuncionarioProcedimentoMapper;
import com.caio.barbearia.repositories.FuncionarioProcedimentoRepository;

import com.caio.barbearia.repositories.FuncionarioRepository;
import com.caio.barbearia.repositories.ProcedimentoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FuncionarioProcedimentoService {

    @Autowired
    private FuncionarioProcedimentoRepository repository;
    
    @Autowired
    private  FuncionarioProcedimentoMapper mapper;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProcedimentoRepository procedimentoRepository;

    public List<FuncionarioProcedimentoResponse> findAll() {
        List<FuncionarioProcedimento> entities = repository.findAll();
        return mapper.toFuncionarioProcedimentoResponseList(entities);
    }

    public FuncionarioProcedimentoResponse findById(Long id) {
        FuncionarioProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id));
        return mapper.toFuncionarioProcedimentoResponse(entity);
    }

    public FuncionarioProcedimentoResponse create(FuncionarioProcedimentoMinRequest request) {
        Optional<Funcionario> entityFuncionario = funcionarioRepository.findById(request.getIdFuncionario());
        Optional<Procedimento> entityProcedimento = procedimentoRepository.findById(request.getIdProcedimento());
        FuncionarioProcedimentoRequest newRequest = new FuncionarioProcedimentoRequest(entityFuncionario, entityProcedimento);

        FuncionarioProcedimento entity = mapper.toFuncionarioProcedimento(newRequest);
        FuncionarioProcedimento savedEntity = repository.save(entity);
        return mapper.toFuncionarioProcedimentoResponse(savedEntity);
    }

    public FuncionarioProcedimentoResponse update(Long id, FuncionarioProcedimentoMinRequest request) {
        FuncionarioProcedimento entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id));

        Optional<Funcionario> entityFuncionario = funcionarioRepository.findById(request.getIdFuncionario());
        Optional<Procedimento> entityProcedimento = procedimentoRepository.findById(request.getIdProcedimento());
        FuncionarioProcedimentoRequest newRequest = new FuncionarioProcedimentoRequest(entityFuncionario, entityProcedimento);

        mapper.updateFuncionarioProcedimentoFromRequest(newRequest, entity);
        FuncionarioProcedimento updatedEntity = repository.save(entity);

        return mapper.toFuncionarioProcedimentoResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Relacionamento não encontrado com o ID: " + id);
        }
        repository.deleteById(id);
    }

    public List<FuncionarioProcedimentoResponse> findFuncionariosByProcedimentoId(Long procedimentoId) {
        List<FuncionarioProcedimento> funcionarios = repository.findFuncionariosByProcedimentoId(procedimentoId);
        return mapper.toFuncionarioProcedimentoResponseList(funcionarios);
   }
}
