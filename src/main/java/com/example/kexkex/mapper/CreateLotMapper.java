package com.example.kexkex.mapper;

import com.example.kexkex.model.Lot;
import com.example.kexkex.dto.CreateLotDTO;

public class CreateLotMapper {
    public static Lot toEntity(CreateLotDTO createdLotDTO){
        Lot lot = new Lot();
        lot.setId(createdLotDTO.getId());
        lot.setLotName(createdLotDTO.getLotName());
        lot.setDescription(createdLotDTO.getDescription());
        lot.setStartPrice(createdLotDTO.getStartPrice());
        lot.setBidPrice(createdLotDTO.getBidPrice());
        return lot;
    }

    public static CreateLotDTO toDTO(Lot lot){
        CreateLotDTO dto = new CreateLotDTO();
        dto.setId(lot.getId());
        dto.setLotName(lot.getLotName());
        dto.setDescription(lot.getDescription());
        dto.setStartPrice(lot.getStartPrice());
        dto.setBidPrice(lot.getBidPrice());
        return dto;
    }
}
