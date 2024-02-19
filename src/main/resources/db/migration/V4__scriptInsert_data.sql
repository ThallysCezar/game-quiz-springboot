-- Inserção de dados da tabela t_theme
INSERT INTO t_theme (id, theme)
VALUES
(1, 'Cinema'),
(2, 'Geografia'),
(3, 'Ciência'),
(4, 'Jogos');

-- Inserção de dados da tabela t_question
INSERT INTO t_question (id, correct_alternativeid, answer, response, fk_theme)
VALUES
(1, 3, 'Qual é o filme mais premiado da história do Oscar?', 'Ben-Hur', 1), -- Cinema
(2, 9, 'Qual é o jogo mais vendido de todos os tempos?', 'Minecraft', 4), -- Jogos
(3, 17, 'Quem foi o inventor da teoria da relatividade?', 'Albert Einstein', 3), -- Ciência
(4, 28, 'Qual é a capital da França?', 'Paris', 2), -- Geografia
(5, 40, 'Qual filme venceu o Oscar de Melhor Filme em 2020?', 'Parasita', 1), -- Cinema
(6, 48, 'Qual jogo é conhecido por popularizar o gênero Battle Royale?', 'PlayerUnknowns Battlegrounds (PUBG)', 4), -- Jogos
(7, 49, 'Quem desenvolveu a teoria quântica?', 'Max Planck', 3), -- Ciência
(8, 59, 'Qual é a capital da Hungria?', 'Budapeste', 2), -- Geografia
(9, 71, 'Quem dirigiu o filme "Interestelar"?', 'Christopher Nolan', 1), -- Cinema
(10, 80, 'Em que ano foi lançado o jogo Minecraft?', '2009', 4), -- Jogos
(11, 87, 'Qual é o elemento mais abundante na crosta terrestre?', 'Oxigênio', 3), -- Ciência
(12, 96, 'Qual é o rio mais longo do mundo?', 'Rio Nilo', 2); -- Geografia

-- Inserção de dados da tabela t_question_choices
INSERT INTO t_question_choices (id, alternative, its_correct, content, fk_question)
VALUES
(1, 'A', true, 'O Senhor dos Anéis: O Retorno do Rei', 1),
(2, 'B', false, 'Titanic', 1),
(3, 'C', false, 'Ben-Hur', 1),
(4, 'D', false, 'Gandhi', 1),
(5, 'E', false, 'Avatar', 1),
(6, 'F', false, 'Forrest Gump', 1),
(7, 'G', false, 'Pulp Fiction', 1),
(8, 'H', false, 'Gladiador', 1),
---- pergunta 2
(9, 'A', true, 'Minecraft', 2),
(10, 'B', false, 'Tetris', 2),
(11, 'C', false, 'Super Mario Bros.', 2),
(12, 'D', false, 'Grand Theft Auto V', 2),
(13, 'E', false, 'The Legend of Zelda: Breath of the Wild', 2),
(14, 'F', false, 'FIFA 18', 2),
(15, 'G', false, 'Fortnite', 2),
(16, 'H', false, 'PlayerUnknowns Battlegrounds', 2),
---- pergunta 3
(17, 'A', true, 'Albert Einstein', 3),
(18, 'B', false, 'Isaac Newton', 3),
(19, 'C', false, 'Galileo Galilei', 3),
(20, 'D', false, 'Marie Curie', 3),
(21, 'E', false, 'Stephen Hawking', 3),
(22, 'F', false, 'Nikola Tesla', 3),
(23, 'G', false, 'Thomas Edison', 3),
(24, 'H', false, 'Marie Skłodowska Curie', 3),
---- pergunta 4
(25, 'A', false, 'Berlim', 4),
(26, 'B', false, 'Madrid', 4),
(27, 'C', false, 'Roma', 4),
(28, 'D', true, 'Paris', 4),
(29, 'E', false, 'Londres', 4),
(30, 'F', false, 'Amsterdã', 4),
(31, 'G', false, 'Atenas', 4),
(32, 'H', false, 'Viena', 4),
---- pergunta 5
(33, 'A', false, 'Os miseráveis', 5),
(34, 'B', false, 'O Poderoso Chefão', 5),
(35, 'C', false, '12 Anos de Escravidão', 5),
(36, 'D', false, 'La La Land', 5),
(37, 'E', false, 'Cidadão Kane', 5),
(38, 'F', false, 'Gladiador', 5),
(39, 'G', false, 'Birdman', 5),
(40, 'H', true, 'Parasita', 5),
---- pergunta 6
(41, 'A', false, 'Call of Duty: Modern Warfare', 6),
(42, 'B', false, 'FIFA 21', 6),
(43, 'C', false, 'The Legend of Zelda: Breath of the Wild', 6),
(44, 'D', false, 'Fortnite', 6),
(45, 'E', false, 'GTA V', 6),
(46, 'F', false, 'Minecraft', 6),
(47, 'G', false, 'Super Mario Bros.', 6),
(48, 'H', true, 'PlayerUnknowns Battlegrounds (PUBG)', 6),
---- pergunta 7
(49, 'A', true, 'Niels Bohr', 7),
(50, 'B', false, 'Max Planck', 7),
(51, 'C', false, 'Werner Heisenberg', 7),
(52, 'D', false, 'Marie Curie', 7),
(53, 'E', false, 'Richard Feynman', 7),
(54, 'F', false, 'Stephen Hawking', 7),
(55, 'G', false, 'Isaac Newton', 7),
(56, 'H', false, 'Albert Einstein', 7),
---- pergunta 8
(57, 'A', false, 'Viena', 8),
(58, 'B', false, 'Praga', 8),
(59, 'C', true, 'Budapeste', 8),
(60, 'D', false, 'Varsóvia', 8),
(61, 'E', false, 'Atenas', 8),
(62, 'F', false, 'Londres', 8),
(63, 'G', false, 'Lisboa', 8),
(64, 'H', false, 'Madrid', 8),
---- pergunta 9
(65, 'A', false, 'Steven Spielberg', 9),
(66, 'B', false, 'Quentin Tarantino', 9),
(67, 'C', false, 'Martin Scorsese', 9),
(68, 'D', false, 'James Cameron', 9),
(69, 'E', true, 'Christopher Nolan', 9),
(70, 'F', false, 'Ridley Scott', 9),
(71, 'G', false, 'David Fincher', 9),
(72, 'H', false, 'Denis Villeneuve', 9),
---- pergunta 10
(73, 'A', false, '2009', 10),
(74, 'B', false, '2010', 10),
(75, 'C', false, '2012', 10),
(76, 'D', false, '2013', 10),
(77, 'E', true, '2011', 10),
(78, 'F', false, '2014', 10),
(79, 'G', false, '2008', 10),
(80, 'H', false, '2015', 10),
---- pergunta 11
(81, 'A', true, 'Silício', 11),
(82, 'B', false, 'Alumínio', 11),
(83, 'C', false, 'Ferro', 11),
(84, 'D', false, 'Cálcio', 11),
(85, 'E', false, 'Sódio', 11),
(86, 'F', false, 'Potássio', 11),
(87, 'G', true, 'Oxigênio', 11),
(88, 'H', false, 'Carbono', 11),
---- pergunta 12
(89, 'A', false, 'Rio Nilo', 12),
(90, 'B', false, 'Rio Mississipi', 12),
(91, 'C', false, 'Rio Yangtzé', 12),
(92, 'D', false, 'Rio Amur', 12),
(93, 'E', false, 'Rio Paraná', 12),
(94, 'F', false, 'Rio Congo', 12),
(95, 'G', false, 'Rio Lena', 12),
(96, 'H', true, 'Rio Amazonas', 12);

------ dados da t_user
INSERT INTO t_user (id, login, password, role)
VALUES
   (1, 'thallys@hotmail.com', '$2a$10$SUluyWEAznJmZK4C4zIKWu79E/lqE/o/I5t30Q1zAcqVVXjr9ZOrq', 0), -- ADMIN
   (2, 'samuel@hotmail.com', '$2a$10$1XyN0tVEqRxkkBxWlr9ee.VuycgefZ3KdXSNbplE0DYad8tbSZ2sa', 1), -- USER
   (3, 'bianca@hotmail.com', '$2a$10$NtoRXsnFElYQIApfjqNPheGGkkpYv3ApazpVo3vqu4VobODrY7vKO', 1), -- USER
   (4, 'robson@hotmail.com', '$2a$10$rCdjJ0TFg.v3BNSbzZ6CdeCK3xyl4cPy8kSXXKqz5jg3ltxMStTHC', 1); -- USER

-- password esta dessa forma, mas, via codigo, termina atualizando para "password" para todos, mas não é uma boa prática
-- deixar a senha pura no banco de dados, então deixei via Hash.

-- inserção de dados da t_player
INSERT INTO t_player (id, nick_name, score, fk_user)
VALUES
(1, 'Thays', 1000, 1),
(2, 'Samuca', 400, 2),
(3, 'BiaBia', 1250, 3),
(4, 'Robinho', 1900, 4);