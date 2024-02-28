package com.mjv.gamequiz;

import com.mjv.gamequiz.controllers.AuthenticationControllerIT;
import com.mjv.gamequiz.controllers.PlayerControllerIT;
import com.mjv.gamequiz.controllers.QuestionControllerIT;
import com.mjv.gamequiz.controllers.UserControllerIT;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;
import org.junit.platform.suite.api.SuiteDisplayName;

@Suite
@SelectClasses({
        UserControllerIT.class,
        PlayerControllerIT.class,
        AuthenticationControllerIT.class,
        QuestionControllerIT.class
})
@SuiteDisplayName("Conjunto de todos os testes de integração")
public class AllIntegrationPart1Test {
}
