# Barbearia API

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-green)](https://spring.io/projects/spring-boot)  
API RESTful para gerenciamento de barbearias, desenvolvida utilizando Spring Boot. Esta API permite a administração de clientes, funcionários, agendamentos, procedimentos, horários e jornadas de trabalho.

## 📋 Funcionalidades

A API é composta pelos seguintes módulos e suas respectivas operações:

### **Clientes**
- Listar todos os clientes.
- Buscar cliente por ID.
- Criar um novo cliente.
- Atualizar informações de um cliente existente.
- Remover um cliente.
- Listar agendamentos de um cliente.

### **Funcionários**
- Listar todos os funcionários.
- Buscar funcionário por ID.
- Criar um novo funcionário.
- Atualizar informações de um funcionário existente.
- Remover um funcionário.
- Listar agendamentos de um funcionário.

### **Procedimentos**
- Listar todos os procedimentos.
- Buscar procedimento por ID.
- Criar um novo procedimento.
- Atualizar informações de um procedimento existente.
- Remover um procedimento.
- Listar funcionários vinculados a um procedimento.

### **Horários**
- Listar horários disponíveis de um funcionário para um dia específico.

### **Jornadas de Trabalho**
- Listar todas as jornadas de trabalho.
- Buscar jornada de trabalho por ID.
- Criar uma nova jornada de trabalho.
- Atualizar informações de uma jornada de trabalho existente.
- Remover uma jornada de trabalho.

### **Agendamentos**
- Listar todos os agendamentos.
- Buscar agendamento por ID.
- Criar um novo agendamento.
- Atualizar informações de um agendamento existente.
- Remover um agendamento.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.0.0**
- **Swagger/OpenAPI** para documentação da API.
- **Banco de dados relacional (ex.: MySQL ou PostgreSQL)**.

## 📚 Documentação

A documentação completa da API pode ser acessada através do endpoint Swagger:

```
http://localhost:8080/swagger-ui/index.html
```

## 🛠️ Instalação e Configuração

1. Clone este repositório:
   ```bash
   git clone https://github.com/CaioChiabai/Barbearia-with-Spring-Boot.git
   ```
2. Navegue até o diretório do projeto:
   ```bash
   cd Barbearia-with-Spring-Boot
   ```
3. Configure o arquivo `application.properties` para conectar ao banco de dados desejado.
4. Compile e inicie o projeto:
   ```bash
   ./mvnw spring-boot:run
   ```

## 🔑 Endpoints Principais

- **Base URL**: `http://localhost:8080`
- **Clientes**: `/cliente`
- **Funcionários**: `/funcionario`
- **Procedimentos**: `/procedimento`
- **Horários Disponíveis**: `/horarios/disponiveis`
- **Jornadas de Trabalho**: `/jornada_trabalho`
- **Agendamentos**: `/agendamento`

## 📝 Exemplos de Requisições

### Buscar cliente por ID

**GET** `/cliente/{id}`  
Exemplo de resposta (200 OK):
```json
{
  "id": 1,
  "nome": "João Silva",
  "email": "joao@email.com",
  "telefone": "123456789"
}
```

### Criar um novo agendamento

**POST** `/agendamento`  
Exemplo de corpo da requisição:
```json
{
  "cliente": { "id": 1 },
  "data": "2024-12-10",
  "horaInicio": { "hour": 14, "minute": 30 },
  "funcionarioProcedimento": { "id": 5 },
  "status": "EM_ABERTO"
}
```

## 🤝 Contribuições

Sinta-se à vontade para contribuir com melhorias. Basta abrir uma _issue_ ou enviar um _pull request_.

## 🛡️ Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.
