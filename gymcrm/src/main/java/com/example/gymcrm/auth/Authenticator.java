package com.example.gymcrm.auth;

import com.example.gymcrm.model.User;

public interface Authenticator {

    boolean authenticate(User user);

}
