package com.example.kexkex.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateLotDTO {
    private Long id;
    private String lotName;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
}
