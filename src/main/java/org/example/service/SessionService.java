package org.example.service;


import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SessionService {



    private final UserRepository userRepository;

    public SessionService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User SessionUse()  {

        org.springframework.security.core.userdetails.User springUser =
                (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return userRepository.findByEmail(springUser.getUsername())
                .orElseThrow(() -> new RuntimeException("user with email not found"));


    }


//    public User sessionUser() {
//        return user;
//    }

    public User sessionUser() {

        var auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth == null || !auth.isAuthenticated() || auth.getName().equals("anonymousUser")) {
            return null;
        }

        // Récupérer email depuis Spring Security
        String email = auth.getName();


        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with email not found"));
    }
}
