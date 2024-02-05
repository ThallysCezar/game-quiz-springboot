package com.mjv.gamequiz.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(
                new Info()
                        .title("Documentação do GameQuiz")
                        .version("1.0")
                        .description("Um Projeto de GameQuiz feito na MJV School Java. "
                                + "O código-fonte do projeto está disponivel no GitHub: "
                                + "https://github.com/ThallysCezar/game-quiz-springboot")
        );
    }
}
