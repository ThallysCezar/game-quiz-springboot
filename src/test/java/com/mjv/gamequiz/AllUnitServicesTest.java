package com.mjv.gamequiz;

import com.mjv.gamequiz.services.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@SelectClasses({
        UserServiceTest.class,
        AuthorizationServiceTest.class,
        PlayerServiceTest.class,
        QuestionAlternativeServiceTest.class,
        QuestionServiceTest.class,
        RankingTopServiceTest.class,
        QuizGameServiceTest.class
})
@RunWith(JUnitPlatform.class)
@SuiteDisplayName("Conjunto de todos os testes unitários")
public class AllUnitServicesTest {
}
