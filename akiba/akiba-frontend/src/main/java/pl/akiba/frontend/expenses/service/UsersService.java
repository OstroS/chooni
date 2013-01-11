package pl.akiba.frontend.expenses.service;

import org.springframework.stereotype.Component;

import pl.akiba.model.entities.User;

@Component("usersService")
public class UsersService {
    
    public User getCurrentUser() {
        User user = new User();
        user.setId(667L);
        user.setName("Krzysztof Madafaka");
        
        return user;
    }
}
