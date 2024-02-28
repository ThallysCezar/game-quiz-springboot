package com.mjv.gamequiz;

import com.mjv.gamequiz.services.*;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        UserServiceTest.class,
        AuthorizationServiceTest.class,
        PlayerServiceTest.class,
        QuestionChoicesServiceTest.class,
        QuestionServiceTest.class,
        RankingTopServiceTest.class,
        QuizGameServiceTest.class
})
@SuiteDisplayName("Conjunto de todos os testes unitários")
public class AllUnitServicesTest {
}
