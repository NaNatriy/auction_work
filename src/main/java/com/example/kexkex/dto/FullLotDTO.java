package com.example.kexkex.dto;

import com.example.kexkex.lotEnum.LotStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class FullLotDTO {
    private Long id;
    private LotStatus status;
    private String lotName;
    private String description;
    private Integer startPrice;
    private Integer bidPrice;
    private Integer currentPrice;
    private BidToLotDTO lastBid;

    public FullLotDTO(Long id, String lotName, String description, LotStatus status, Integer startPrice, Integer bidPrice) {
    }

}
