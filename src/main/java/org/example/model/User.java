package org.example.model;


import com.fasterxml.jackson.annotation.JsonTypeId;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Entity
public class User {

    public User(String firstName, String lastName, String email, String password) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;


    }

    public User(UserAccount account) {
        this.account = account;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private  String email;
    private String password;
    @ManyToMany
    private List<Link> links;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private UserAccount account;

}
