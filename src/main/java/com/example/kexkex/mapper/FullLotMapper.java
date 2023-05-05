package com.example.kexkex.mapper;

import com.example.kexkex.dto.FullLotDTO;
import com.example.kexkex.dto.LotDTO;
import com.example.kexkex.model.Lot;

public class FullLotMapper {
    public static FullLotDTO toFullDTO (LotDTO lotDTO) {
        FullLotDTO dto = new FullLotDTO();
        dto.setId(lotDTO.getId());
        dto.setStatus(lotDTO.getStatus());
        dto.setLotName(lotDTO.getLotName());
        dto.setDescription(lotDTO.getDescription());
        dto.setStartPrice(lotDTO.getStartPrice());
        dto.setBidPrice(lotDTO.getBidPrice());
        return dto;
    }

    public static FullLotDTO toDTO (Lot lot) {
        FullLotDTO dto = new FullLotDTO();
        dto.setId(lot.getId());
        dto.setStatus(lot.getStatus());
        dto.setLotName(lot.getLotName());
        dto.setDescription(lot.getDescription());
        dto.setStartPrice(lot.getStartPrice());
        dto.setBidPrice(lot.getBidPrice());
        return dto;
    }

}
