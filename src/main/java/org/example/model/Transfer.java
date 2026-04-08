package org.example.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDateTime date;
    @ManyToOne
    private User fromUser;
    @ManyToOne
    private User toUser;
    private Double amountBeforeFee;
    private Double amountAfterFee;


    public Transfer() {}

}