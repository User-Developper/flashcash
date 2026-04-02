package org.example.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
@Entity
public class UserAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountId;


    private Double amount;
    private String iban;


    public UserAccount plus(double amount) {

        this.amount += amount;
        return this;
    }

    public UserAccount minus(double amount) {

        this.amount -= amount;

        return this;

    }

}
