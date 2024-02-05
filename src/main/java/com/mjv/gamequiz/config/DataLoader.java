package com.mjv.gamequiz.config;

import com.mjv.gamequiz.domains.Player;
import com.mjv.gamequiz.domains.Question;
import com.mjv.gamequiz.domains.QuestionAlternative;
import com.mjv.gamequiz.domains.User;
import com.mjv.gamequiz.repositories.PlayerRepository;
import com.mjv.gamequiz.repositories.QuestionAlternativeRepository;
import com.mjv.gamequiz.repositories.QuestionRepository;
import com.mjv.gamequiz.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;
    private final QuestionAlternativeRepository questionAlternativeRepository;
    private final PlayerRepository playerRepository;

    @Override
    @Transactional
    public void run(String... args) {
        System.out.println(">>> Iniciando carga de dados...");

        if (userRepository.count() == 0 && playerRepository.count() == 0) {
            loadUserData();
        }

        if (questionAlternativeRepository.count() == 0) {
            loadQuestionAlternativeData();
            loadQuestionData();

        }
    }

    @Bean
    public CommandLineRunner loadData() {
        return (args) -> {
            loadUserData();
            loadQuestionData();
            loadQuestionAlternativeData();
        };
    }

    private void loadUserData() {
        List<User> userList = initializeUserList();
        userRepository.saveAll(userList);
    }

    private void loadQuestionData() {
        List<Question> questionList = initializeQuestionList();
        questionRepository.saveAll(questionList);
    }

    private void loadQuestionAlternativeData() {
        List<QuestionAlternative> questionAlternativeList = initializeQuestionAlternativeList();
        questionAlternativeRepository.saveAll(questionAlternativeList);
    }

    @Bean
    public List<QuestionAlternative> initializeQuestionAlternativeList() {
        List<QuestionAlternative> alternatives = new ArrayList<>();
        List<Question> questions = initializeQuestionList();

        // Pergunta 1
        alternatives.add(new QuestionAlternative(1L, "A", true, "O Senhor dos Anéis: O Retorno do Rei", questions.get(0)));
        alternatives.add(new QuestionAlternative(2L, "B", false, "Titanic", questions.get(0)));
        alternatives.add(new QuestionAlternative(3L, "C", false, "Ben-Hur", questions.get(0)));
        alternatives.add(new QuestionAlternative(4L, "D", false, "Gandhi", questions.get(0)));
        alternatives.add(new QuestionAlternative(5L, "E", false, "Avatar", questions.get(0)));
        alternatives.add(new QuestionAlternative(6L, "F", false, "Forrest Gump", questions.get(0)));
        alternatives.add(new QuestionAlternative(7L, "G", false, "Pulp Fiction", questions.get(0)));
        alternatives.add(new QuestionAlternative(8L, "H", false, "Gladiador", questions.get(0)));

        // Pergunta 2
        alternatives.add(new QuestionAlternative(9L, "A", true, "Minecraft", questions.get(1)));
        alternatives.add(new QuestionAlternative(10L, "B", false, "Tetris", questions.get(1)));
        alternatives.add(new QuestionAlternative(11L, "C", false, "Super Mario Bros.", questions.get(1)));
        alternatives.add(new QuestionAlternative(12L, "D", false, "Grand Theft Auto V", questions.get(1)));
        alternatives.add(new QuestionAlternative(13L, "E", false, "The Legend of Zelda: Breath of the Wild", questions.get(1)));
        alternatives.add(new QuestionAlternative(14L, "F", false, "FIFA 18", questions.get(1)));
        alternatives.add(new QuestionAlternative(15L, "G", false, "Fortnite", questions.get(1)));
        alternatives.add(new QuestionAlternative(16L, "H", true, "PlayerUnknown's Battlegrounds", questions.get(1)));

        // Pergunta 3
        alternatives.add(new QuestionAlternative(17L, "A", true, "Albert Einstein", questions.get(2)));
        alternatives.add(new QuestionAlternative(18L, "B", false, "Isaac Newton", questions.get(2)));
        alternatives.add(new QuestionAlternative(19L, "C", false, "Galileo Galilei", questions.get(2)));
        alternatives.add(new QuestionAlternative(20L, "D", false, "Marie Curie", questions.get(2)));
        alternatives.add(new QuestionAlternative(21L, "E", false, "Stephen Hawking", questions.get(2)));
        alternatives.add(new QuestionAlternative(22L, "F", false, "Nikola Tesla", questions.get(2)));
        alternatives.add(new QuestionAlternative(23L, "G", false, "Thomas Edison", questions.get(2)));
        alternatives.add(new QuestionAlternative(24L, "H", false, "Marie Skłodowska Curie", questions.get(2)));

        // Pergunta 4
        alternatives.add(new QuestionAlternative(25L, "A", false, "Berlim", questions.get(3)));
        alternatives.add(new QuestionAlternative(26L, "B", false, "Madrid", questions.get(3)));
        alternatives.add(new QuestionAlternative(27L, "C", false, "Roma", questions.get(3)));
        alternatives.add(new QuestionAlternative(28L, "D", true, "Paris", questions.get(3)));
        alternatives.add(new QuestionAlternative(29L, "E", false, "Londres", questions.get(3)));
        alternatives.add(new QuestionAlternative(30L, "F", false, "Amsterdã", questions.get(3)));
        alternatives.add(new QuestionAlternative(31L, "G", false, "Atenas", questions.get(3)));
        alternatives.add(new QuestionAlternative(32L, "H", false, "Viena", questions.get(3)));

        // Pergunta 5
        alternatives.add(new QuestionAlternative(33L, "A", false, "Os miseráveis", questions.get(4)));
        alternatives.add(new QuestionAlternative(34L, "B", false, "O Poderoso Chefão", questions.get(4)));
        alternatives.add(new QuestionAlternative(35L, "C", false, "12 Anos de Escravidão", questions.get(4)));
        alternatives.add(new QuestionAlternative(36L, "D", false, "La La Land", questions.get(4)));
        alternatives.add(new QuestionAlternative(37L, "E", false, "Cidadão Kane", questions.get(4)));
        alternatives.add(new QuestionAlternative(38L, "F", false, "Gladiador", questions.get(4)));
        alternatives.add(new QuestionAlternative(39L, "G", false, "Birdman", questions.get(4)));
        alternatives.add(new QuestionAlternative(40L, "H", true, "Parasita", questions.get(4)));

        // Pergunta 6
        alternatives.add(new QuestionAlternative(41L, "A", false, "Call of Duty: Modern Warfare", questions.get(5)));
        alternatives.add(new QuestionAlternative(42L, "B", false, "FIFA 21", questions.get(5)));
        alternatives.add(new QuestionAlternative(43L, "C", false, "The Legend of Zelda: Breath of the Wild", questions.get(5)));
        alternatives.add(new QuestionAlternative(44L, "D", false, "Fortnite", questions.get(5)));
        alternatives.add(new QuestionAlternative(45L, "E", false, "GTA V", questions.get(5)));
        alternatives.add(new QuestionAlternative(46L, "F", false, "Minecraft", questions.get(5)));
        alternatives.add(new QuestionAlternative(47L, "G", false, "Super Mario Bros.", questions.get(5)));
        alternatives.add(new QuestionAlternative(48L, "H", true, "PlayerUnknown's Battlegrounds (PUBG)", questions.get(5)));

        // Pergunta 7
        alternatives.add(new QuestionAlternative(49L, "A", true, "Niels Bohr", questions.get(6)));
        alternatives.add(new QuestionAlternative(50L, "B", false, "Max Planck", questions.get(6)));
        alternatives.add(new QuestionAlternative(51L, "C", false, "Werner Heisenberg", questions.get(6)));
        alternatives.add(new QuestionAlternative(52L, "D", false, "Marie Curie", questions.get(6)));
        alternatives.add(new QuestionAlternative(53L, "E", false, "Richard Feynman", questions.get(6)));
        alternatives.add(new QuestionAlternative(54L, "F", false, "Stephen Hawking", questions.get(6)));
        alternatives.add(new QuestionAlternative(55L, "G", false, "Isaac Newton", questions.get(6)));
        alternatives.add(new QuestionAlternative(56L, "H", false, "Albert Einstein", questions.get(6)));

        // Pergunta 8
        alternatives.add(new QuestionAlternative(57L, "A", false, "Praga", questions.get(7)));
        alternatives.add(new QuestionAlternative(58L, "B", false, "Viena", questions.get(7)));
        alternatives.add(new QuestionAlternative(59L, "C", true, "Budapeste", questions.get(7)));
        alternatives.add(new QuestionAlternative(60L, "D", false, "Varsóvia", questions.get(7)));
        alternatives.add(new QuestionAlternative(61L, "E", false, "Atenas", questions.get(7)));
        alternatives.add(new QuestionAlternative(62L, "F", false, "Londres", questions.get(7)));
        alternatives.add(new QuestionAlternative(63L, "G", false, "Lisboa", questions.get(7)));
        alternatives.add(new QuestionAlternative(64L, "H", false, "Madrid", questions.get(7)));

        // Pergunta 9
        alternatives.add(new QuestionAlternative(65L, "A", false, "A Forca", questions.get(8)));
        alternatives.add(new QuestionAlternative(66L, "B", false, "Banco Imobiliário", questions.get(8)));
        alternatives.add(new QuestionAlternative(67L, "C", true, "Xadrez", questions.get(8)));
        alternatives.add(new QuestionAlternative(68L, "D", false, "Poker", questions.get(8)));
        alternatives.add(new QuestionAlternative(69L, "E", false, "Monopoly", questions.get(8)));
        alternatives.add(new QuestionAlternative(70L, "F", false, "Dominó", questions.get(8)));
        alternatives.add(new QuestionAlternative(71L, "G", false, "Dama", questions.get(8)));
        alternatives.add(new QuestionAlternative(72L, "H", false, "Cara a Cara", questions.get(8)));

        // Pergunta 10
        alternatives.add(new QuestionAlternative(73L, "A", true, "Marie Curie", questions.get(9)));
        alternatives.add(new QuestionAlternative(74L, "B", false, "Nikola Tesla", questions.get(9)));
        alternatives.add(new QuestionAlternative(75L, "C", false, "Galileo Galilei", questions.get(9)));
        alternatives.add(new QuestionAlternative(76L, "D", false, "Isaac Newton", questions.get(9)));
        alternatives.add(new QuestionAlternative(77L, "E", false, "Max Planck", questions.get(9)));
        alternatives.add(new QuestionAlternative(78L, "F", false, "Werner Heisenberg", questions.get(9)));
        alternatives.add(new QuestionAlternative(79L, "G", false, "Niels Bohr", questions.get(9)));
        alternatives.add(new QuestionAlternative(80L, "H", false, "Richard Feynman", questions.get(9)));

        // Pergunta 11
        alternatives.add(new QuestionAlternative(81L, "A", false, "Nova York", questions.get(10)));
        alternatives.add(new QuestionAlternative(82L, "B", false, "Pequim", questions.get(10)));
        alternatives.add(new QuestionAlternative(83L, "C", false, "Tóquio", questions.get(10)));
        alternatives.add(new QuestionAlternative(84L, "D", false, "Moscou", questions.get(10)));
        alternatives.add(new QuestionAlternative(85L, "E", false, "Cairo", questions.get(10)));
        alternatives.add(new QuestionAlternative(86L, "F", false, "Londres", questions.get(10)));
        alternatives.add(new QuestionAlternative(87L, "G", false, "Roma", questions.get(10)));
        alternatives.add(new QuestionAlternative(88L, "H", true, "Cidade do México", questions.get(10)));

        // Pergunta 12
        alternatives.add(new QuestionAlternative(89L, "A", false, "Dama com Arminho", questions.get(11)));
        alternatives.add(new QuestionAlternative(90L, "B", false, "O Grito", questions.get(11)));
        alternatives.add(new QuestionAlternative(91L, "C", false, "Guernica", questions.get(11)));
        alternatives.add(new QuestionAlternative(92L, "D", false, "A Noite Estrelada", questions.get(11)));
        alternatives.add(new QuestionAlternative(93L, "E", false, "Mona Lisa", questions.get(11)));
        alternatives.add(new QuestionAlternative(94L, "F", false, "Os Girassóis", questions.get(11)));
        alternatives.add(new QuestionAlternative(95L, "G", false, "O Nascimento de Vênus", questions.get(11)));
        alternatives.add(new QuestionAlternative(96L, "H", true, "Pablo Picasso", questions.get(11)));

        return alternatives;
    }

    @Bean
    public List<Question> initializeQuestionList() {
        List<Question> questionsList = new ArrayList<>();

        // Pergunta 1
        Question questionOne = new Question(1L, "Cinema", "Qual é o filme mais premiado da história do Oscar?", "O Senhor dos Anéis: O Retorno do Rei", 1L, null);
        questionsList.add(questionOne);

        ArrayList<QuestionAlternative> alternativesOne = new ArrayList<>();
        alternativesOne.add(new QuestionAlternative(1L, "A", true, "O Senhor dos Anéis: O Retorno do Rei", questionOne));
        alternativesOne.add(new QuestionAlternative(2L, "B", false, "Titanic", questionOne));
        alternativesOne.add(new QuestionAlternative(3L, "C", false, "Ben-Hur", questionOne));
        alternativesOne.add(new QuestionAlternative(4L, "D", false, "Gandhi", questionOne));
        alternativesOne.add(new QuestionAlternative(5L, "E", false, "Avatar", questionOne));
        alternativesOne.add(new QuestionAlternative(6L, "F", false, "Forrest Gump", questionOne));
        alternativesOne.add(new QuestionAlternative(7L, "G", false, "Pulp Fiction", questionOne));
        alternativesOne.add(new QuestionAlternative(8L, "H", false, "Gladiador", questionOne));

        questionOne.setQuestionAlternativeArrayList(alternativesOne);

        // Pergunta 2
        Question questionTwo = new Question(2L, "Jogos", "Qual é o jogo mais vendido de todos os tempos?", "Minecraft", 2L, null);
        questionsList.add(questionTwo);

        ArrayList<QuestionAlternative> alternativesTwo = new ArrayList<>();
        alternativesTwo.add(new QuestionAlternative(9L, "A", true, "Minecraft", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(10L, "B", false, "Tetris", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(11L, "C", false, "Super Mario Bros.", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(12L, "D", false, "Grand Theft Auto V", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(13L, "E", false, "The Legend of Zelda: Breath of the Wild", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(14L, "F", false, "FIFA 18", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(15L, "G", false, "Fortnite", questionsList.get(1)));
        alternativesTwo.add(new QuestionAlternative(16L, "H", false, "PlayerUnknown's Battlegrounds", questionsList.get(1)));

        questionTwo.setQuestionAlternativeArrayList(alternativesTwo);

        // Pergunta 3
        Question questionThree = new Question(3L, "Ciência", "Quem foi o inventor da teoria da relatividade?", "Albert Einstein", 3L, null);
        questionsList.add(questionThree);

        ArrayList<QuestionAlternative> alternativesThree = new ArrayList<>();
        alternativesThree.add(new QuestionAlternative(17L, "A", true, "Albert Einstein", questionThree));
        alternativesThree.add(new QuestionAlternative(18L, "B", false, "Isaac Newton", questionThree));
        alternativesThree.add(new QuestionAlternative(19L, "C", false, "Galileo Galilei", questionThree));
        alternativesThree.add(new QuestionAlternative(20L, "D", false, "Marie Curie", questionThree));
        alternativesThree.add(new QuestionAlternative(21L, "E", false, "Stephen Hawking", questionThree));
        alternativesThree.add(new QuestionAlternative(22L, "F", false, "Nikola Tesla", questionThree));
        alternativesThree.add(new QuestionAlternative(23L, "G", false, "Thomas Edison", questionThree));
        alternativesThree.add(new QuestionAlternative(24L, "H", false, "Marie Skłodowska Curie", questionThree));
        questionThree.setQuestionAlternativeArrayList(alternativesThree);

        // Pergunta 4
        Question questionFour = new Question(4L, "Geografia", "Qual é a capital da França?", "Paris", 28L, null);
        questionsList.add(questionFour);

        ArrayList<QuestionAlternative> alternativesFour = new ArrayList<>();
        alternativesFour.add(new QuestionAlternative(25L, "A", false, "Berlim", questionFour));
        alternativesFour.add(new QuestionAlternative(26L, "B", false, "Madrid", questionFour));
        alternativesFour.add(new QuestionAlternative(27L, "C", false, "Roma", questionFour));
        alternativesFour.add(new QuestionAlternative(28L, "D", true, "Paris", questionFour));
        alternativesFour.add(new QuestionAlternative(29L, "E", false, "Londres", questionFour));
        alternativesFour.add(new QuestionAlternative(30L, "F", false, "Amsterdã", questionFour));
        alternativesFour.add(new QuestionAlternative(31L, "G", false, "Atenas", questionFour));
        alternativesFour.add(new QuestionAlternative(32L, "H", false, "Viena", questionFour));
        questionFour.setQuestionAlternativeArrayList(alternativesFour);

        // Pergunta 5
        Question questionFive = new Question(5L, "Cinema", "Qual filme venceu o Oscar de Melhor Filme em 2020?", "Parasita", 40L, null);
        questionsList.add(questionFive);

        ArrayList<QuestionAlternative> alternativesFive = new ArrayList<>();
        alternativesFive.add(new QuestionAlternative(33L, "A", false, "Os miseráveis", questionFive));
        alternativesFive.add(new QuestionAlternative(34L, "B", false, "O Poderoso Chefão", questionFive));
        alternativesFive.add(new QuestionAlternative(35L, "C", false, "12 Anos de Escravidão", questionFive));
        alternativesFive.add(new QuestionAlternative(36L, "D", false, "La La Land", questionFive));
        alternativesFive.add(new QuestionAlternative(37L, "E", false, "Cidadão Kane", questionFive));
        alternativesFive.add(new QuestionAlternative(38L, "F", false, "Gladiador", questionFive));
        alternativesFive.add(new QuestionAlternative(39L, "G", false, "Birdman", questionFive));
        alternativesFive.add(new QuestionAlternative(40L, "H", true, "Parasita", questionFive));
        questionFive.setQuestionAlternativeArrayList(alternativesFive);

        // Pergunta 6
        Question questionSix = new Question(6L, "Jogos", "Qual jogo é conhecido por popularizar o gênero Battle Royale?", "PlayerUnknown's Battlegrounds (PUBG)", 48L, null);
        questionsList.add(questionSix);

        ArrayList<QuestionAlternative> alternativesSix = new ArrayList<>();
        alternativesSix.add(new QuestionAlternative(41L, "A", false, "Call of Duty: Modern Warfare", questionSix));
        alternativesSix.add(new QuestionAlternative(42L, "B", false, "FIFA 21", questionSix));
        alternativesSix.add(new QuestionAlternative(43L, "C", false, "The Legend of Zelda: Breath of the Wild", questionSix));
        alternativesSix.add(new QuestionAlternative(44L, "D", false, "Fortnite", questionSix));
        alternativesSix.add(new QuestionAlternative(45L, "E", false, "GTA V", questionSix));
        alternativesSix.add(new QuestionAlternative(46L, "F", false, "Minecraft", questionSix));
        alternativesSix.add(new QuestionAlternative(47L, "G", false, "Super Mario Bros.", questionSix));
        alternativesSix.add(new QuestionAlternative(48L, "H", true, "PlayerUnknown's Battlegrounds (PUBG)", questionSix));
        questionSix.setQuestionAlternativeArrayList(alternativesSix);

        // Pergunta 7
        Question questionSeven = new Question(7L, "Ciência", "Quem desenvolveu a teoria quântica?", "Niels Bohr", 49L, null);
        questionsList.add(questionSeven);

        ArrayList<QuestionAlternative> alternativesSeven = new ArrayList<>();
        alternativesSeven.add(new QuestionAlternative(49L, "A", true, "Niels Bohr", questionSeven));
        alternativesSeven.add(new QuestionAlternative(50L, "B", false, "Max Planck", questionSeven));
        alternativesSeven.add(new QuestionAlternative(51L, "C", false, "Werner Heisenberg", questionSeven));
        alternativesSeven.add(new QuestionAlternative(52L, "D", false, "Marie Curie", questionSeven));
        alternativesSeven.add(new QuestionAlternative(53L, "E", false, "Richard Feynman", questionSeven));
        alternativesSeven.add(new QuestionAlternative(54L, "F", false, "Stephen Hawking", questionSeven));
        alternativesSeven.add(new QuestionAlternative(55L, "G", false, "Isaac Newton", questionSeven));
        alternativesSeven.add(new QuestionAlternative(56L, "H", false, "Albert Einstein", questionSeven));
        questionSeven.setQuestionAlternativeArrayList(alternativesSeven);

        // Pergunta 8
        Question questionEight = new Question(8L, "Geografia", "Qual é a capital da Hungria?", "Budapeste", 59L, null);
        questionsList.add(questionEight);

        ArrayList<QuestionAlternative> alternativesEight = new ArrayList<>();
        alternativesEight.add(new QuestionAlternative(57L, "A", false, "Viena", questionEight));
        alternativesEight.add(new QuestionAlternative(58L, "B", false, "Praga", questionEight));
        alternativesEight.add(new QuestionAlternative(59L, "C", true, "Budapeste", questionEight));
        alternativesEight.add(new QuestionAlternative(60L, "D", false, "Varsóvia", questionEight));
        alternativesEight.add(new QuestionAlternative(61L, "E", false, "Bucareste", questionEight));
        alternativesEight.add(new QuestionAlternative(62L, "F", false, "Zagreb", questionEight));
        alternativesEight.add(new QuestionAlternative(63L, "G", false, "Belgrado", questionEight));
        alternativesEight.add(new QuestionAlternative(64L, "H", false, "Eslováquia", questionEight));
        questionEight.setQuestionAlternativeArrayList(alternativesEight);

        // Pergunta 9
        Question questionNine = new Question(9L, "Cinema", "Quem dirigiu o filme \"Interestelar\"?", "Christopher Nolan", 71L, null);
        questionsList.add(questionNine);

        ArrayList<QuestionAlternative> alternativesNine = new ArrayList<>();
        alternativesNine.add(new QuestionAlternative(65L, "A", false, "Steven Spielberg", questionNine));
        alternativesNine.add(new QuestionAlternative(66L, "B", false, "Quentin Tarantino", questionNine));
        alternativesNine.add(new QuestionAlternative(67L, "C", false, "Martin Scorsese", questionNine));
        alternativesNine.add(new QuestionAlternative(68L, "D", false, "James Cameron", questionNine));
        alternativesNine.add(new QuestionAlternative(69L, "E", true, "Christopher Nolan", questionNine));
        alternativesNine.add(new QuestionAlternative(70L, "F", false, "Ridley Scott", questionNine));
        alternativesNine.add(new QuestionAlternative(71L, "G", false, "David Fincher", questionNine));
        alternativesNine.add(new QuestionAlternative(72L, "H", false, "Denis Villeneuve", questionNine));
        questionNine.setQuestionAlternativeArrayList(alternativesNine);

        // Pergunta 10
        Question questionTen = new Question(10L, "Jogos", "Em que ano foi lançado o jogo Minecraft?", "2011", 80L, null);
        questionsList.add(questionTen);

        ArrayList<QuestionAlternative> alternativesTen = new ArrayList<>();
        alternativesTen.add(new QuestionAlternative(73L, "A", false, "2009", questionTen));
        alternativesTen.add(new QuestionAlternative(74L, "B", false, "2010", questionTen));
        alternativesTen.add(new QuestionAlternative(75L, "C", false, "2012", questionTen));
        alternativesTen.add(new QuestionAlternative(76L, "D", false, "2013", questionTen));
        alternativesTen.add(new QuestionAlternative(77L, "E", true, "2011", questionTen));
        alternativesTen.add(new QuestionAlternative(78L, "F", false, "2014", questionTen));
        alternativesTen.add(new QuestionAlternative(79L, "G", false, "2008", questionTen));
        alternativesTen.add(new QuestionAlternative(80L, "H", false, "2015", questionTen));
        questionTen.setQuestionAlternativeArrayList(alternativesTen);

        // Pergunta 11
        Question questionEleven = new Question(11L, "Ciência", "Qual é o elemento mais abundante na crosta terrestre?", "Oxigênio", 89L, null);
        questionsList.add(questionEleven);

        ArrayList<QuestionAlternative> alternativesEleven = new ArrayList<>();
        alternativesEleven.add(new QuestionAlternative(81L, "A", false, "Silício", questionEleven));
        alternativesEleven.add(new QuestionAlternative(82L, "B", false, "Alumínio", questionEleven));
        alternativesEleven.add(new QuestionAlternative(83L, "C", false, "Ferro", questionEleven));
        alternativesEleven.add(new QuestionAlternative(84L, "D", false, "Cálcio", questionEleven));
        alternativesEleven.add(new QuestionAlternative(85L, "E", false, "Sódio", questionEleven));
        alternativesEleven.add(new QuestionAlternative(86L, "F", false, "Potássio", questionEleven));
        alternativesEleven.add(new QuestionAlternative(87L, "G", true, "Oxigênio", questionEleven));
        alternativesEleven.add(new QuestionAlternative(88L, "H", false, "Carbono", questionEleven));
        questionEleven.setQuestionAlternativeArrayList(alternativesEleven);

        // Pergunta 12
        Question questionTwelve = new Question(12L, "Geografia", "Qual é o rio mais longo do mundo?", "Rio Amazonas", 98L, null);
        questionsList.add(questionTwelve);

        ArrayList<QuestionAlternative> alternativesTwelve = new ArrayList<>();
        alternativesTwelve.add(new QuestionAlternative(89L, "A", false, "Rio Nilo", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(90L, "B", false, "Rio Mississipi", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(91L, "C", false, "Rio Yangtzé", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(92L, "D", false, "Rio Amur", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(93L, "E", false, "Rio Paraná", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(94L, "F", false, "Rio Congo", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(95L, "G", false, "Rio Lena", questionTwelve));
        alternativesTwelve.add(new QuestionAlternative(96L, "H", true, "Rio Amazonas", questionTwelve));
        questionTwelve.setQuestionAlternativeArrayList(alternativesTwelve);

        return questionsList;
    }

    @Bean
    public List<User> initializeUserList() {
        List<User> userList = new ArrayList<>();

        // Thallys Cezar
        User user1 = new User();
        user1.setName("Thallys Cezar");
        user1.setFullName("Thallys Cezar");
        user1.setAge(25);
        user1.setEmail("thallys@example.com");
        user1.setPassword("password1");

        Player player1 = new Player();
        player1.setScore(1000);
        player1.setUser(user1);
        player1.setNickName("Thays");
        player1.setTheme("Ciência");

        playerRepository.save(player1);
        user1.setPlayer(player1);
        userList.add(user1);

        // Samuel
        User user2 = new User();
        user2.setName("Samuel");
        user2.setFullName("Samuel");
        user2.setAge(22);
        user2.setEmail("samuel@example.com");
        user2.setPassword("password2");

        Player player2 = new Player();
        player2.setScore(400);
        player2.setUser(user2);
        player2.setTheme("");
        player2.setNickName("Samuca");
        player2.setTheme("Geografia");

        playerRepository.save(player2);
        user2.setPlayer(player2);
        userList.add(user2);

        // Bianca
        User user3 = new User();
        user3.setName("Bianca");
        user3.setFullName("Bianca");
        user3.setAge(23);
        user3.setEmail("bianca@example.com");
        user3.setPassword("password3");

        Player player3 = new Player();
        player3.setScore(1250);
        player3.setUser(user3);
        player3.setNickName("BiaBia");
        player3.setTheme("Jogos");

        playerRepository.save(player3);
        user3.setPlayer(player3);
        userList.add(user3);

        // Robson
        User user4 = new User();
        user4.setName("Robson");
        user4.setFullName("Robson");
        user4.setAge(28);
        user4.setEmail("robson@example.com");
        user4.setPassword("password4");

        Player player4 = new Player();
        player4.setScore(1900);
        player4.setUser(user4);
        player4.setNickName("Robinho");
        player4.setTheme("Cinema");

        playerRepository.save(player4);
        user4.setPlayer(player4);
        userList.add(user4);

        return userList;
    }

}