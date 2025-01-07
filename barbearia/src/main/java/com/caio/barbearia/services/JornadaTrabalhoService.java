package com.caio.barbearia.services;

import com.caio.barbearia.dto.request.JornadaTrabalho.JornadaTrabalhoMinRequest;
import com.caio.barbearia.dto.request.JornadaTrabalho.JornadaTrabalhoRequest;
import com.caio.barbearia.dto.response.JornadaTrabalho.JornadaTrabalhoResponse;
import com.caio.barbearia.entities.Funcionario;
import com.caio.barbearia.entities.JornadaTrabalho;
import com.caio.barbearia.exceptions.ResourceNotFoundException;
import com.caio.barbearia.mapper.JornadaTrabalhoMapper;
import com.caio.barbearia.repositories.FuncionarioRepository;
import com.caio.barbearia.repositories.JornadaTrabalhoRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JornadaTrabalhoService {

    @Autowired
    private JornadaTrabalhoRepository repository;

    @Autowired
    private JornadaTrabalhoMapper mapper;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<JornadaTrabalhoResponse> findAll() {
        List<JornadaTrabalho> entities = repository.findAll();
        return mapper.toJornadaTrabalhoResponseList(entities);
    }

    public JornadaTrabalhoResponse findById(Long id) {
        JornadaTrabalho entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jornada de trabalho não encontrada com o ID: " + id));
        return mapper.toJornadaTrabalhoResponse(entity);
    }

    public JornadaTrabalhoResponse create(JornadaTrabalhoMinRequest request) {

        Optional<Funcionario> entityFuncionario = funcionarioRepository.findById(request.getIdfuncionario());
        JornadaTrabalho newEntity = new JornadaTrabalho(
                                                        entityFuncionario,
                                                        request.getInicioJornada(),
                                                        request.getFimJornada(),
                                                        request.getInicioIntervalo(),
                                                        request.getFimIntervalo());
        JornadaTrabalho savedEntity = repository.save(newEntity);
        return mapper.toJornadaTrabalhoResponse(savedEntity);
    }

    public JornadaTrabalhoResponse update(Long id, JornadaTrabalhoMinRequest request) {
        JornadaTrabalho entity = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Jornada de trabalho não encontrada com o ID: " + id));

        Optional<Funcionario> entityFuncionario = funcionarioRepository.findById(request.getIdfuncionario());
        JornadaTrabalhoRequest newRequest = new JornadaTrabalhoRequest(
                entityFuncionario,
                request.getInicioJornada(),
                request.getFimJornada(),
                request.getInicioIntervalo(),
                request.getFimIntervalo());

        mapper.updateJornadaTrabalhoFromRequest(newRequest, entity);
        JornadaTrabalho updatedEntity = repository.save(entity);
        return mapper.toJornadaTrabalhoResponse(updatedEntity);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Jornada de trabalho não encontrada com o ID: " + id);
        }
        repository.deleteById(id);
    }
}
