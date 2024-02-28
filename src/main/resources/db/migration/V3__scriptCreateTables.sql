-- Criando tabela de usuarios
CREATE TABLE IF NOT EXISTS public.t_user
(
    id bigint NOT NULL,
    login character varying(255) COLLATE pg_catalog."default" NOT NULL,
    password character varying(255) COLLATE pg_catalog."default" NOT NULL,
    role smallint,
    CONSTRAINT t_user_pkey PRIMARY KEY (id),
    CONSTRAINT uk_t_user_login UNIQUE (login)
);

-- Criando a tabela de temas
CREATE TABLE IF NOT EXISTS public.t_theme
(
    id bigint NOT NULL,
    theme varchar(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT t_theme_pkey PRIMARY KEY (id)
);

-- Criando a tabela de questões
CREATE TABLE IF NOT EXISTS public.t_question
(
    id bigint NOT NULL,
    correct_alternativeid bigint,
    answer varchar(200) NOT NULL,
    response varchar(200) NOT NULL,
    fk_theme bigint,
    CONSTRAINT t_question_pkey PRIMARY KEY (id),
    CONSTRAINT fk_t_question_theme FOREIGN KEY (fk_theme)
        REFERENCES public.t_theme (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Criando a tabela de alternativas, questionChoices
CREATE TABLE IF NOT EXISTS public.t_question_choices
(
    id bigint NOT NULL,
    alternative varchar(200) COLLATE pg_catalog."default" NOT NULL,
    its_correct boolean,
    content varchar(200) NOT NULL,
    fk_question bigint,
    CONSTRAINT t_question_choices_pkey PRIMARY KEY (id),
    CONSTRAINT fk_t_question_choices_question FOREIGN KEY (fk_question)
        REFERENCES public.t_question (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

-- Criando a tabela de jogadores
CREATE TABLE IF NOT EXISTS public.t_player
(
    id bigint NOT NULL,
    nick_name character varying(200) COLLATE pg_catalog."default" NOT NULL,
    score integer,
    fk_user bigint,
    CONSTRAINT t_player_pkey PRIMARY KEY (id),
    CONSTRAINT uk_t_player_fk_user UNIQUE (fk_user),
    CONSTRAINT fk_t_player_user FOREIGN KEY (fk_user)
        REFERENCES public.t_user (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);