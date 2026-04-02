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
    @ManyToMany
    private User from;
    @ManyToMany
    private User to;
    private Double amountBeforeFee;
    private Double amountAfterFee;


}