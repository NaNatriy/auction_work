package com.example.kexkex.dto;

import com.example.kexkex.lotEnum.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class LotDTO {
    private Long id;
    private LotStatus status;
    private String lotName;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
}
