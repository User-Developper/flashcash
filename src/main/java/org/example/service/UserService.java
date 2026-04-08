package org.example.service;


import org.example.model.User;
import org.example.model.UserAccount;
import org.example.repository.UserRepository;
import org.example.service.form.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;


    public User findUser(String email) {

        Optional<User> user = userRepository.findByEmail(email);
//
//        user.ifPresent(u ->  {
//
//            System.out.println("L'utilisateur existe : " + u.getEmail());
//
//
//
//        });

        //   return userRepository.findByEmail(email);

        return user
                .orElseThrow(() -> new RuntimeException("User not found"));


    }

    public void createUser(SignupForm signupForm) {

        Optional<User> userOn = userRepository.findByEmail(signupForm.getEmail());

        userOn.ifPresent(u -> {


            // System.out.println("L'utilisateur existe deja");

            throw new RuntimeException("L'utilisateur existe deja");

        });


        User user2 = new User();
        user2.setEmail(signupForm.getEmail());
        user2.setFirstName(signupForm.getFirstName());
        user2.setLastName(signupForm.getLastName());
        UserAccount account = new UserAccount();
        account.setAmount(0.0);
        user2.setAccount(account);
        user2.setPassword(passwordEncoder.encode(signupForm.getPassword()));
        userRepository.save(user2);



    }
}
