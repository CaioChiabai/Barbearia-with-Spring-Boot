[JAVA_BADGE]: https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white
[SPRING_BADGE]: https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white
[MYSQL_BADGE]: https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white
[DOCKER_BADGE]: https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white
[APACHE_MAVEN_BADGE]: https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white
[SWAGGER_BADGE]: https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white

<h1 align="center" style="font-weight: bold;">API de Barbearia</h1>

<div style="text-align: center;">

  ![Java][JAVA_BADGE]
  ![Spring][SPRING_BADGE]
  ![MySQL][MYSQL_BADGE]
  ![Docker][DOCKER_BADGE]
  ![Apache Maven][APACHE_MAVEN_BADGE]
  ![Swagger][SWAGGER_BADGE]

</div>
<p align="center">
  <a href="#started">Como utilizar</a> • 
  <a href="#routes">Alguns Endpoints</a> •
</p>

<p align="center">
  <b>API REST para gerenciamento de barbearias, consolidando práticas de desenvolvimento moderno.</b>
</p>

<h2>💻 O que você encontrará?</h2>
 
* Java
* Spring Boot
* MySQL
* Autenticação e Segurança com Spring Security  
* Mapeamento ORM com Spring Data JPA  
* Testes Unitários com JUnit e Mockito
* Flyway  
* Docker  
* Padrão MVC
* DTOs
* Exception Handler
* Documentação com Swagger

<h2>💡 Funcionalidades</h2>

Desenvolvi o back-end de uma API REST, que Gerencia uma Barbearia utilizando Java com Spring Boot. 
Implementei autenticação e segurança por meio de um controle de acessos baseado em cargos (roles), 
garantindo que os usuários tenham permissões específicas para os recursos disponíveis. 
A aplicação conecta-se a um banco de dados MySQL, proporcionando persistência segura e eficiente dos dados. 
Entre as funcionalidades implementadas estão:

* Gerenciamento de Usuários (Cadastro, Login e Controle de Acesso com JWT).
* CRUD de Procedimentos (Corte de cabelo, barba, etc.).
* Agendamento de horários para clientes.
* Consulta de horários disponíveis para funcionários.
* Vinculação de funcionários a procedimentos.
* Controle e registro de jornadas de trabalho dos funcionários.

<h2 id="started">🚀 Como utilizar?</h2>

### Pré-requisitos

1. **Instalar Docker Desktop**  
   Baixe e instale o Docker Desktop:  

   - [Docker Desktop para Windows/Mac](https://www.docker.com/products/docker-desktop)

   No Linux, instale o Docker Engine e Docker Compose:
   ```bash
   sudo apt-get update
   sudo apt-get install docker-ce docker-ce-cli containerd.io docker-compose-plugin
   ```

2. **Verifique a instalação do Docker**  
   Execute os comandos no terminal:
   ```bash
   docker --version
   docker-compose --version
   ```

3. **Baixar o Postman**  
   Ferramenta recomendada para interagir com os endpoints da API:
   - [Postman](https://www.postman.com/downloads/)

### 🛠️ Como rodar a aplicação

1. **Clone o repositório**
   ```bash
   git clone <URL_DO_REPOSITORIO>
   ```
2. **Acesse o diretório do projeto**
   ```bash
   cd <NOME_DO_DIRETORIO>
   ```
3. **Construa e inicie os contêineres**
   ```bash
   docker-compose up --build
   ```
4. **Verifique se os contêineres estão rodando**
   ```bash
   docker ps
   ```
5. **Acesse a documentação Swagger**
   Abra o navegador e vá até: [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html).


<h2>OverView dos endpoints</h2>

![image](https://github.com/user-attachments/assets/08e6a2e4-648d-40fd-9ebe-b7e15c81106d)
![image](https://github.com/user-attachments/assets/cd2560ac-893f-46d8-be23-c90aa110e591)
![image](https://github.com/user-attachments/assets/ca6c4137-c850-4a99-928f-515aea86292c)
![image](https://github.com/user-attachments/assets/c9b26689-120f-4864-892d-4333eaa55df3)
![image](https://github.com/user-attachments/assets/6bbf709b-e2b8-476a-901c-da946532c0fc)
![image](https://github.com/user-attachments/assets/a398bcb6-e0d7-4862-83af-a57830dcf610)
![image](https://github.com/user-attachments/assets/1ae4211c-5c92-43c7-922c-660bb471857b)
![image](https://github.com/user-attachments/assets/7eacee57-85dc-4d0a-b5a0-deb1eb5b298b)

<h2 id="routes">📍 Alguns Endpoints da API</h2>

### Autenticação

**Endpoint:** `POST /auth/login`

***REQUEST***
```json
{
  "email": "caio",
  "password": "123456"
}
```

***RESPONSE***
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJhdXRoLWFwaSIsInN1YiI6InVzdWFyaW8iLCJleHAiOjE3Mjc0MDUwMzd9.mIXYswRCpKSt-MmQ8C0n-OhGkAPSyOU7nyAkVOnmMWI"
}
```

### Listar todos os Procedimentos

**Endpoint:** `GET /procedimento`

***RESPONSE***
```json
[
  {
    "id": 1,
    "nome": "Corte de cabelo",
    "preco": 50.0,
    "duracao": "00:30:00"
  },
  {
    "id": 2,
    "nome": "Barba",
    "preco": 30.0,
    "duracao": "00:30:00"
  }
]
```

### Criar um Agendamento

**Endpoint:** `POST /agendamento`

***REQUEST***
```json
{
  "idCliente": 1,
  "horaInicio": "14:30:00",
  "data": "2025-01-20",
  "idFuncionarioProcedimento": 3,
  "status": "EM_ABERTO"
}
```

***RESPONSE***
```json
{
  "id": 5,
  "cliente": {
    "id": 1,
    "nome": "João Silva",
    "telefone": "11912345678"
  },
  "horaInicio": "14:30:00",
  "data": "2025-01-20",
  "funcionarioProcedimento": {
    "id": 3,
    "funcionario": {
      "id": 2,
      "nome": "Carlos Souza",
      "cargo": "Barbeiro"
    },
    "procedimento": {
      "id": 1,
      "nome": "Corte de cabelo"
    }
  },
  "status": "EM_ABERTO"
}
```

**Confira o restante dos endpoints na documentação do Swagger!**
