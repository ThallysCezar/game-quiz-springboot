
# Game Quiz

Bem-vindo ao GameQuiz! Este é um projeto de quiz de perguntas e respostas desenvolvido em Java com Spring Boot. O sistema permite criar perguntas e associar alternativas a essas perguntas. Este README fornecerá informações sobre como configurar, executar e entender a estrutura do projeto.


## Stack utilizada

**Back-end:** Java, Spring Boot, PostgreSQL + DBeaver, JUnit + Mockito para testes unitários, Spring Security + JWT para autenticação, Flyway para migração de banco de dados.


## Configuração

- Certifique-se de ter o Java e o Maven instalados em seu sistema antes de prosseguir.

```bash
  git clone https://github.com/ThallysCezar/game-quiz-springboot.git
  cd gamequiz
```

- Execute o projeto usando o Maven

```bash
  mvn spring-boot:run
```
- ### Configuração do Banco de Dados

1. **Instalação do PostgreSQL**: Se você ainda não tem o PostgreSQL instalado em seu sistema, siga as instruções de instalação fornecidas no site oficial do PostgreSQL para o seu sistema operacional.

2. **Criação do Banco de Dados**: Após a instalação, você precisará criar um banco de dados para o seu aplicativo. Você pode fazer isso usando a ferramenta de linha de comando `psql` fornecida com o PostgreSQL ou uma interface gráfica como o pgAdmin.

   ```bash
   psql -U postgres
   CREATE DATABASE gamequiz;
   ```
   Substitua 'postgres' pelo seu nome de usuário do PostgreSQL, se necessário.
3. Configuração do Flyway: O Flyway é uma ferramenta de migração de banco de dados que permite gerenciar e versionar alterações em seu banco de dados. Certifique-se de ter o Flyway instalado e configurado em seu projeto. O arquivo flyway.conf contém as configurações para o Flyway, incluindo o nome do banco de dados. Certifique-se de alterar ou criar um banco de dados com o mesmo nome especificado no flyway.conf.
   - Execute o comando depois da criação do banco de dados, via Flyway:
    ```bash
      mvn flyway:migrate
    ```
    
5. Configuração do Spring Boot: Agora que o banco de dados está pronto, você precisa configurar seu projeto Spring Boot para usar o PostgreSQL. No arquivo application.properties:
   ```properties
   # PostgreSQL
    spring.datasource.url=jdbc:postgresql://localhost:5432/gamequiz
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    spring.datasource.driver-class-name=org.postgresql.Driver
    spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
   ```
   Certifique-se de substituir 'seu_usuario' e 'sua_senha' pelo seu nome de usuário e senha do PostgreSQL, respectivamente. O URL jdbc também pode precisar ser ajustado dependendo da configuração do seu PostgreSQL.
   
6. Teste de Conexão: Após configurar tudo, reinicie seu aplicativo Spring Boot e verifique se ele consegue se conectar ao banco de dados PostgreSQL corretamente.
    
## Documentação da API
- QuestionController

### Recupera todas as perguntas disponíveis.

```http
  GET /question
```

### Recupera uma pergunta específica com base no ID fornecido.

```http
  GET /question/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id da sua pergunta  |

### Recupera uma questão baseada no seu tema.

```http
  GET /questions/theme/{themeName}
```

| Parâmetro   | Tipo       | Descrição                                                    |
|:------------| :--------- |:-------------------------------------------------------------|
| `themeName` | `String` | **Obrigatório**. O tema para recuperar uma questão por tema. |

### Cria uma nova pergunta. JSON exemplo:

```http
  POST /questions
```
```JSON
  {
    "theme": "Geografia",
    "question": "Qual é a capital da França?",
    "response": "Paris",
    "correctQuestionAlternativeID": 1,
    "listaAquestionAlternativeDTOListlternativas": [
    {
      "alternativa": "Berlim",
      "correta": false
    },
    {
      "alternativa": "Madri",
      "correta": false
    },
    {
      "alternativa": "Paris",
      "correta": true
    },
    {
      "alternativa": "Roma",
      "correta": false
    },
    {
      "alternativa": "Luxenburgo",
      "correta": false
    },
    {
      "alternativa": "Pernambuco",
      "correta": false
    },
    {
      "alternativa": "California",
      "correta": false
    },
    {
      "alternativa": "Lugano",
      "correta": false
    }
  ]
}
```

- QuestionAlternativeController

### Recupera todas as perguntas disponíveis.
```http
  GET /questionsAlternatives
```

### Recupera uma pergunta específica com base no ID fornecido.

```http
  GET /questionsAlternatives/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id da alternativa da pergunta  |

### Recupera uma alternativa de pergunta específica com base no ID fornecido.

```http
  GET /questionsAlternatives/question-id/{id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `id` | `Long` | **Obrigatório**. O id da pergunta para achar a alternativa |

### Recupera a contagem de uma questão baseada no seu tema.

```http
  GET /questionsAlternatives/count-by-theme
```

### Cria uma nova pergunta. JSON exemplo:

```http
  POST /questionsAlternatives
```
```JSON
  {
    "alternative": "Resposta A",
    "reference": "Ref A"
  }

```
### Cria uma lista nova de perguntas. JSON exemplo:

```http
  POST /questionsAlternatives/list
```
```JSON
  [
    {
      "alternative": "Resposta A",
      "reference": "Ref A"
    },
    {
      "alternative": "Resposta B",
      "reference": "Ref B"
    },
    {
      "alternative": "Resposta C",
      "reference": "Ref C"
    },
    {
      "alternative": "Resposta D",
      "reference": "Ref D"
    },
    {
      "alternative": "Resposta E",
      "reference": "Ref E"
    },
    {
      "alternative": "Resposta F",
      "reference": "Ref F"
    },
    {
      "alternative": "Resposta G",
      "reference": "Ref G"
    },
    {
      "alternative": "Resposta H",
      "reference": "Ref H"
    }
]
```

- UserController
### Recupera todos os usuários disponíveis.

```http
  GET /users
```

### Recupera um usuário específico com base no ID fornecido.

```http
  GET /users/{id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `Long` | **Obrigatório**. O ID do user que você quer |

### Recupera um usuário específico com base no Email e Password fornecidos.

```http
  GET /users/{email}/{password}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `email`      | `String` | **Obrigatório**. O emaill do user que você quer |
| `password`      | `String` | **Obrigatório**. O password do user que você quer |

### Cria um novo usuário. JSON exemplo:

```http
  POST /users
```
```JSON
 {
    "name": "usuarioTeste",
    "fullName": "Usuário de Teste",
    "age": 25,
    "email": "usuario.teste@email.com",
    "password": "senha123"
  }
```

- AuthenticationController
### Registrar um usuário.

```http
  POST /auth/register
```
```JSON
 {
      "login": "robson@hotmail.com",
      "password": "123456789",
      "role": "USER"
}
```

### Recupera o token, ao fazer login, com login e password validos.

```http
  POST /auth/login
```
```JSON
 {
      "login": "thallys@hotmail.com",
      "password": "123456789"
  }
```

- RankingTopController
### Recupera os scores e o nickName dos players e faz um ranking dos scores.

```http
  GET /ranking-players
```

- QuizController
### Verifica o user, pelo login, e recupera as questões por temas.

```http
  GET /game-quiz/{login}/{theme}
```

| Parâmetro   | Tipo       | Descrição                                                        |
| :---------- | :--------- |:-----------------------------------------------------------------|
| `login`      | `String` | **Obrigatório**. O login(email) do user que você quer verificar. |
| `theme`      | `String` | **Obrigatório**. O questões por tema que você quer recuperar.    |

### Game-Quiz, onde o jogo irá funcionar.
```http
  POST /game-quiz/answer
```
```JSON
 {
      "questionId": 5,
      "chosenAlternativeId": 40,
      "nickName": "Samuca"
  }
```
| Parâmetro   | Tipo       | Descrição                                                        |
| :---------- | :--------- |:-----------------------------------------------------------------|
| `questionId`    | `Long` | **Obrigatório**. Id da questão. |
| `chosenAlternativeId`    | `Long` | **Obrigatório**. Id da alternativa, o qual você acha que é a certa.    |
| `nickName`      | `String` | **Obrigatório**. Nickname do player, caso acerte será acrescentado score.    |

- Nicknames:
  - "Thays"
  - "Samuca"
  - "Robinho"
  - "BiaBia"
 
Observação: Certifique  de estar autenticado e de posse do token de verificação para fazer a requisição dos endpoints, ou irá mostrar que você não tem acesso, 403 Forbidden.

### Documentação com Swagger
A documentação completa da API pode ser encontrada no Swagger também. Para acessar a documentação, siga as etapas abaixo:

1. Certifique-se de que o projeto esteja em execução.

2. Abra um navegador da web e vá para a seguinte URL:

   [Swagger API Documentation](http://localhost:8080/swagger-ui/index.html)

3. Isso abrirá a interface do Swagger, onde você pode explorar e testar os endpoints da API, apenas se tiver autenticado.
4. Use os exemplos com JSON com que já tem disponível aqui, ficará mais fácil para testar.

## Autenticação com o Spring Security e JWT
Para acessar os endpoints protegidos, é necessário autenticar-se usando JWT. Siga as instruções abaixo para autenticar-se:

1. Faça uma solicitação POST para /authenticate com as credenciais de login no corpo da solicitação. Aqui estão as credenciais de login:
   - 'thallys@hotmail.com', senha: '123456789'
   - 'samuel@hotmail.com', senha: '123456789'
   - 'bianca@hotmail.com', senha: '123456789'
   - 'robson@hotmail.com', senha: '123456789'
2. Você receberá um token JWT como resposta.
3. Copie o token JWT recebido.
4. Abra o Swagger e clique no botão "Authorize" no canto superior direito.
5. No campo "Value", cole o token JWT copiado.
6. Agora você está autenticado e pode acessar os endpoints protegidos.

OBS: Os usuários têm duas roles: ADMIN e USER. Apenas o usuário com o email 'thallys@hotmail.com' tem a role ADMIN e pode acessar endpoints que exigem permissões de ADMIN:
- POST, PUT e DELETE de qualquer endpoint.
- E acessar e modificar todos os outros endpoints que não sejam esses.

Divirta-se explorando a API!

## Como o jogo funciona
Para começar a jogar, siga os passos abaixo:
1. Autenticação
    - Faça login com seu usuário e senha em:
        ```http
            POST /auth/login
        ```
    - Esta requisição retornará um token de autenticação que deve ser usado nas próximas requisições.

2. Obtenção de Questão Aleatória por Tema:
    - Após estar em posse do token para autenticação, utilize o endpoint abaixo para obter uma questão aleatória com base no tema escolhido:
        ```http
            GET /game-quiz/{login}/{theme}
        ```
    - Este endpoint retorna a questão com suas alternativas.

3. Envio da Resposta da Questão:
    - Após obter a questão, envie sua resposta utilizando o endpoint:
        ```http
            POST /game-quiz/answer
        ```
    - Se a resposta estiver correta, o jogador receberá 100 pontos de score.

4. Consulta do Ranking de Jogadores:
    - Para visualizar o ranking dos jogadores com as maiores pontuações, utilize o endpoint:
        ```http
            GET /ranking-players
        ```
Observação: As questões são geradas aleatoriamente com base no tema escolhido. Caso tenha dificuldades para acessar os endpoints fornecidos, consulte como deve ser usado cada endpoint na documentação da API.

## Sobre o projeto

Este projeto foi desenvolvido como parte do curso da School MJV de Java. Ele serviu como uma oportunidade para revisar conceitos básicos de Java com Spring Boot, explorando a criação de APIs RESTful, manipulação de banco de dados e interações entre entidades. Se você encontrar problemas ou tiver sugestões de melhoria, sinta-se à vontade para abrir uma issue ou enviar um pull request. Divirta-se explorando e desenvolvendo!

## Contribuindo

Contribuições são sempre bem-vindas!

Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir um problema ou enviar uma solicitação pull.

