package pl.akiba.frontend.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;
import pl.akiba.model.entities.User.ROLE;
import pl.akiba.wsclient.client.DefaultUserClient;
import pl.akiba.wsclient.client.UserClient;

public class UsersServiceImpl implements UsersService {

    @Override
    public FacebookUser getByFacebookId(Long facebookId) {
        //FIXME
        UserClient userClient = new DefaultUserClient("http://localhost:8080/akiba-backend/");
        return userClient.getUser(facebookId);
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
