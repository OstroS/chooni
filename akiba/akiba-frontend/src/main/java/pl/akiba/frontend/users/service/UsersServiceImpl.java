package pl.akiba.frontend.users.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import pl.akiba.model.entities.FacebookUser;
import pl.akiba.model.entities.User;
import pl.akiba.wsclient.client.DefaultUserClient;
import pl.akiba.wsclient.client.UserClient;

public class UsersServiceImpl implements UsersService {

    
    @Override
    public FacebookUser getByFacebookId(Long facebookId) {
        UserClient userClient = new DefaultUserClient();
        return userClient.getUser(facebookId);
    }

    @Override
    public List<GrantedAuthority> getUsersAuthorities(User user) {
        List<String> authorities = user.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        for(String authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority));
        }
        
        return grantedAuthorities;
    }

}
