package pl.akiba.backend.service;

import java.io.IOException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/pl/akiba/backend/appContextTest.xml" })
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private final Random random = new Random();

    @Test
    public void testGetFacebookUser() {
        FacebookUser facebookUser = null;
        try {
            facebookUser = userService.getFacebookUser(random.nextInt(100000));
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(facebookUser);
    }

}
