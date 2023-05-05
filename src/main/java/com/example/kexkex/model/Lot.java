package com.example.kexkex.model;

import com.example.kexkex.lotEnum.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Lot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lotName;
    private String description;
    @Enumerated(EnumType.STRING)
    private LotStatus status;
    private Integer startPrice;
    private Integer bidPrice;
    @OneToMany(mappedBy = "lot", cascade = CascadeType.ALL)
    private List<Bid> bids;


}
