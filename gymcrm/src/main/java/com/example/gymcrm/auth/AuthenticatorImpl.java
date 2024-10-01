package com.example.gymcrm.auth;

import com.example.gymcrm.dao.core.UserDAO;
import com.example.gymcrm.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatorImpl implements Authenticator {

    private final UserDAO userDAO;

    @Autowired
    public AuthenticatorImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean authenticate(User user) {
        return userDAO.findById(user.getId())
                .filter(value -> value.getUsername().equals(user.getUsername())
                        && value.getPassword().equals(user.getPassword())).isPresent();
    }
}
