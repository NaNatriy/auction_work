package com.example.kexkex.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@NoArgsConstructor
@Data
public class BidToLotDTO {
    private String bidName;
    private LocalDateTime bidTime;
}
