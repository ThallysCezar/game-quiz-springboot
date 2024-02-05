
# Game Quiz

Bem-vindo ao GameQuiz! Este é um projeto de quiz de perguntas e respostas desenvolvido em Java com Spring Boot. O sistema permite criar perguntas e associar alternativas a essas perguntas. Este README fornecerá informações sobre como configurar, executar e entender a estrutura do projeto.


## Stack utilizada

**Back-end:** Java, Spring Boot e PostgreSQL como banco de dados.


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
- Configuração do Banco de dados:
    Atualmente, o projeto utiliza um banco de dados H2 para facilitar o desenvolvimento. Se você deseja migrar para o PostgreSQL, modifique as configurações em src/main/resources/application.properties conforme necessário.
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

### Documentação com Swagger
A documentação completa da API pode ser encontrada no Swagger também. Para acessar a documentação, siga as etapas abaixo:

1. Certifique-se de que o projeto esteja em execução.

2. Abra um navegador da web e vá para a seguinte URL:

   [Swagger API Documentation](http://localhost:8080/swagger-ui/index.html)

3. Isso abrirá a interface do Swagger, onde você pode explorar e testar os endpoints da API.
4. Use os exemplos com JSON com que já tem disponível aqui, ficará mais fácil para testar.

## Contexto do Projeto

Este projeto foi desenvolvido como parte do curso da School MJV de Java. Ele serviu como uma oportunidade para revisar conceitos básicos de Java com Spring Boot, explorando a criação de APIs RESTful, manipulação de banco de dados e interações entre entidades. Se você encontrar problemas ou tiver sugestões de melhoria, sinta-se à vontade para abrir uma issue ou enviar um pull request. Divirta-se explorando e desenvolvendo!


## Contribuindo

Contribuições são sempre bem-vindas!

Se você encontrar problemas ou tiver sugestões de melhorias, sinta-se à vontade para abrir um problema ou enviar uma solicitação pull.

