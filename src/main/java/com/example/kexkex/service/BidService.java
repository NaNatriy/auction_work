package com.example.kexkex.service;

import com.example.kexkex.dto.BidDTO;
import com.example.kexkex.mapper.BidToLotMapper;
import com.example.kexkex.mapper.LotMapper;
import com.example.kexkex.repository.BidRepository;
import com.example.kexkex.model.Bid;
import com.example.kexkex.model.Lot;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
public class BidService {

    private final BidRepository bidRepository;
    private final LotService lotService;

    public BidService(BidRepository bidRepository, LotService lotService) {
        this.bidRepository = bidRepository;
        this.lotService = lotService;
    }

    public BidDTO createBid(BidDTO bidDTO) {
        Lot lot = LotMapper.toEntity(lotService.getLotById(bidDTO.getLotId()));
        Bid bid = BidToLotMapper.toEntity(bidDTO);
        bid.setBidTime(LocalDateTime.now());
        bid.setLot(lot);

        Bid createBid = bidRepository.save(bid);
        return BidToLotMapper.toDTO(createBid);
    }
}
