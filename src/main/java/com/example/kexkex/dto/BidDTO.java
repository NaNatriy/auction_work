package com.example.kexkex.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@Data
public class BidDTO {
    @JsonIgnore
    private Long id;
    private String bidName;
    @JsonIgnore
    private LocalDateTime bidTime;
    @JsonIgnore
    private Long lotId;
}
