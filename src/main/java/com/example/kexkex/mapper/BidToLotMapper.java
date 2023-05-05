package com.example.kexkex.mapper;

import com.example.kexkex.dto.BidDTO;
import com.example.kexkex.model.Bid;

public class BidToLotMapper {
    public static BidDTO toDTO(Bid bid) {
        BidDTO dto = new BidDTO();
        dto.setId(bid.getId());
        dto.setBidName(bid.getBidName());
        dto.setBidTime(bid.getBidTime());
        return dto;
    }

    public static Bid toEntity(BidDTO bidDTO) {
        Bid bid = new Bid();
        bid.setId(bidDTO.getId());
        bid.setBidName(bidDTO.getBidName());
//        bid.setBidTime(bidDTO.getBidTime());
        return bid;
    }


}
