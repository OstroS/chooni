package pl.akiba.frontend.expenses.service;

import java.security.Principal;
import java.util.logging.Logger;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import pl.akiba.frontend.model.entities.FacebookUser;
import pl.akiba.model.entities.User;

@Component("usersService")
public class UsersService {
    
    private static final Logger logger = Logger.getLogger(UsersService.class.toString());

    public User getCurrentUser() {
        User user = new User();
        user.setId(667L);
        user.setName("Krzysztof Madafaka");

        return user;
    }

    public User getCurrentUser(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken) {
            Object principalItself = ((UsernamePasswordAuthenticationToken) principal).getPrincipal();

            if (principalItself instanceof FacebookUser) {
                return (User) principalItself;
            } else if (principalItself instanceof User) {
                return (User) principalItself;
            } else {
                logger.info("User is not of proper type");
                throw new AccessDeniedException("Nieprawidłowy użyszkodnik!");
            }
        }
        else {
            logger.info("User is not authenticated properly");
            throw new AccessDeniedException("Nieprawidłowy użyszkodnik!");
        }

    }
}