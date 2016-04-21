package ru.stqa.cources.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.cources.mantis.appmanager.HttpSession;

import java.io.IOException;


/**
 * Created by leonto on 4/21/2016.
 */
public class LoginTests extends TestBase{

    @Test
    public void testLogin() throws IOException {
        HttpSession session = app.newSession();
        Assert.assertTrue(session.login("administrator", "root"));
        Assert.assertTrue(session.isLoggedInAs("administrator"));

//        assertTrue(session.login("administrator", "root"));
//        assertTrue(session.isLoggedInAs("administrator"));
    }
}
