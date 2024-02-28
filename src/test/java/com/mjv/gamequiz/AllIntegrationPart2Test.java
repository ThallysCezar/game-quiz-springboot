package com.mjv.gamequiz;

import com.mjv.gamequiz.controllers.*;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;
import org.junit.runner.RunWith;

@Suite
@SelectClasses({
        AlternativeControllerIT.class,
        QuizControllerIT.class,
        RankingTopControllerIT.class,
})
@SuiteDisplayName("Conjunto de todos os testes de integração")
public class AllIntegrationPart2Test {
}
