package ru.stqa.cources.mantis.tests;

import org.testng.annotations.Test;

/**
 * Created by leonto on 4/22/2016.
 */
public class RegistrationTests extends TestBase{

    @Test
    public void testRegistration(){
        app.registration().start("user1", "user1@localhost.localdomain");

    }
}
