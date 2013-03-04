package pl.akiba.wsclient.client;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.UserService;

/**
 * 
 * @author sobczakt
 */
public class UserClientTest extends CommonClientTest {

    private final UserService userService;

    public UserClientTest() {
        this.userService = new DefaultUserClient("http://localhost:8080/akiba-backend",
                httpClientFactory.getHttpClient());
    }

    @Test
    public void testGetFacebookUser() {
        FacebookUser facebookUser = null;

        try {
            facebookUser = userService.getFacebookUser(random.nextLong());
        } catch (StatusException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(facebookUser);
    }

}
