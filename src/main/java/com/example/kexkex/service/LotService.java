package com.example.kexkex.service;

import com.example.kexkex.dto.BidToLotDTO;
import com.example.kexkex.dto.CreateLotDTO;
import com.example.kexkex.dto.FullLotDTO;
import com.example.kexkex.dto.LotDTO;
import com.example.kexkex.lotEnum.LotStatus;
import com.example.kexkex.mapper.CreateLotMapper;
import com.example.kexkex.mapper.FullLotMapper;
import com.example.kexkex.mapper.LotMapper;
import com.example.kexkex.model.Lot;
import com.example.kexkex.repository.BidRepository;
import com.example.kexkex.repository.BidTarget;
import com.example.kexkex.repository.LotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Slf4j
@Service
public class LotService {

    private final LotRepository lotRepository;
    private final BidRepository bidRepository;

    public LotService(LotRepository lotRepository, BidRepository bidRepository) {
        this.lotRepository = lotRepository;
        this.bidRepository = bidRepository;
    }


    public LotDTO getLotById(Long id) {
        return LotMapper.toDTO(lotRepository.findById(id).orElse(null));
    }

    public LotDTO createLot(CreateLotDTO createdLotDTO) {
        Lot lot = CreateLotMapper.toEntity(createdLotDTO);
        lot.setStatus(LotStatus.CREATED);
        Lot createdLot = lotRepository.save(lot);
        return LotMapper.toDTO(createdLot);
    }

    public void actionLot(Long lotId) {
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.ACTION);
        lotRepository.save(lot);
    }

    public void soldLot(Long lotId) {
        Lot lot = lotRepository.findById(lotId).get();
        lot.setStatus(LotStatus.SOLD);
        lotRepository.save(lot);
    }

    public BidTarget getFirstBid(Long id) {
        return bidRepository.getFirstBid(id);
    }

    public BidTarget getContinualBid(Long id) {
        return bidRepository.getContinualBid(id);
    }

    public FullLotDTO getFullLot(Long id) {
        FullLotDTO fullLotDTO = FullLotMapper.toFullDTO(getLotById(id));
        Integer currentPrice = currentPrice(id, fullLotDTO.getBidPrice(), fullLotDTO.getStartPrice());
        fullLotDTO.setCurrentPrice((currentPrice));
        fullLotDTO.setLastBid(getAboutLastBid(id));
        return fullLotDTO;
    }

    public Collection<LotDTO> getAllLots(LotStatus lotStatus) {
        return lotRepository.findAllByStatus(lotStatus).stream()
                .map(LotMapper::toDTO)
                .collect(Collectors.toList());
    }

    public Collection<FullLotDTO> getAllLotsForCVS() {
        return lotRepository.findAll().stream()
                .map(FullLotMapper::toDTO)
                .peek(lot -> lot.setCurrentPrice
                        ((currentPrice(lot.getId(),
                        lot.getBidPrice(), lot.getStartPrice()))))
                .peek(lot -> lot.setLastBid(getAboutLastBid(lot.getId())))
                .collect(Collectors.toList());
    }

    private Integer currentPrice(Long id, Integer bidPrice, Integer startPrice) {
        return (int) (bidRepository.bidCount(id) * bidPrice + startPrice);
    }

    private BidToLotDTO getAboutLastBid(Long id) {
            BidToLotDTO bidDTO = new BidToLotDTO();
            bidDTO.setBidName(bidRepository.getLastBid(id).getBidName());
            bidDTO.setBidTime(bidRepository.getLastBid(id).getBidTime());
            return bidDTO;
    }
}
