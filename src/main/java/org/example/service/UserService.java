package org.example.service;


import org.example.model.User;
import org.example.model.UserAccount;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {


    @Autowired
    UserRepository userRepository;




    public User findUser(String email){

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

    public User createUser(String firstName, String lastName, String email, String password){

        Optional<User> userOn = userRepository.findByEmail(email);

        userOn.ifPresent( u ->{


           // System.out.println("L'utilisateur existe deja");

            throw new RuntimeException("L'utilisateur existe deja");

        });


        User  user = new User(firstName, lastName, email, password);

        UserAccount account = new UserAccount();

        account.setAmount(0.0);

        user.setAccount(account);

        userRepository.save(user);



        return user;


    }
}
