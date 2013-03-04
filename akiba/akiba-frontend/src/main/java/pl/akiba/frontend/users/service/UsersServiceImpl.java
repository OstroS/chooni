package pl.akiba.frontend.users.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.eclipse.jetty.client.HttpClient;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;
import pl.akiba.model.entities.User.ROLE;
import pl.akiba.model.exception.StatusException;
import pl.akiba.model.service.UserService;
import pl.akiba.wsclient.client.DefaultUserClient;
import pl.akiba.wsclient.client.factory.JettyHttpClientConf.Builder;
import pl.akiba.wsclient.client.factory.JettyHttpClientFactory;

@Component("usersService")
public class UsersServiceImpl implements UsersService {

    private static Logger logger = Logger.getLogger(UsersServiceImpl.class.toString());
    
    @Override
    //FIXME logging & httpclient
    public FacebookUser getByFacebookId(Long facebookId) {
        //@formatter:off
        Builder httpClientConfBuilder = new Builder()
            .withConnectorType(HttpClient.CONNECTOR_SELECT_CHANNEL)
            .withMaxConnectionsPerAddress(100)
            .withConnectBlocking(false)
            .withIdleTimeout(1500)
            .withMaxThreads(100)
            .withTimeout(1500)
            .withMaxRetries(5);
        //@formatter:on

        JettyHttpClientFactory httpClientFactory = new JettyHttpClientFactory(httpClientConfBuilder.build());

        UserService userClient = new DefaultUserClient("http://localhost:8080/akiba-backend",
                httpClientFactory.getHttpClient());

        try {
            FacebookUser fUser = userClient.getFacebookUser(facebookId);
            logger.info("Returned facebook user: " + fUser);
            return fUser;
        } catch (StatusException | IOException | InterruptedException exception) {
            logger.severe(exception.toString());
        }

        return null;
    }

    @Override
    public List<GrantedAuthority> getUsersAuthorities(User user) {
        List<ROLE> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for (ROLE authority : authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.name()));
        }

        return grantedAuthorities;
    }

}
