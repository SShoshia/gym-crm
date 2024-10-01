package com.example.gymcrm.auth;

import com.example.gymcrm.model.entity.User;

public interface Authenticator {

    boolean authenticate(User user);

}
