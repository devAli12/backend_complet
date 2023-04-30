package com.form.fiche.services;


import com.form.fiche.entities.User;

import java.util.Optional;

public interface UserService {
    User findByEmail(String email);

    User save (User user);
    boolean resetPassword(String email);
    String confirmToken(String token);

    void  updatePassword(String email ,String newPassword);
    Optional<User> findById(Long id);
}
