package com.example.kexkex.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Bid {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String bidName;
    private LocalDateTime bidTime;
    @ManyToOne(fetch = FetchType.LAZY)
    private Lot lot;


}