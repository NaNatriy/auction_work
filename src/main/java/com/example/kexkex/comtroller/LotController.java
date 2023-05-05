package com.example.kexkex.comtroller;

import com.example.kexkex.dto.BidDTO;
import com.example.kexkex.dto.CreateLotDTO;
import com.example.kexkex.dto.FullLotDTO;
import com.example.kexkex.dto.LotDTO;
import com.example.kexkex.lotEnum.LotStatus;
import com.example.kexkex.model.Bid;
import com.example.kexkex.model.Lot;
import com.example.kexkex.repository.BidTarget;
import com.example.kexkex.service.BidService;
import com.example.kexkex.service.LotService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Collection;

@RestController
@RequestMapping("lot")
public class LotController {

    private final LotService lotService;
    private final BidService bidService;

    public LotController(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }

//////////////////////////////////////////Lot////////////////////////////////////////////
    @PostMapping
    public ResponseEntity<LotDTO> createLot(@RequestBody CreateLotDTO createdLotDTO) {
        LotDTO createdLot = lotService.createLot(createdLotDTO);
        return ResponseEntity.ok(createdLot);
    }

    @PostMapping("/{id}/active")
    public ResponseEntity<Lot> actionLot(@PathVariable Long id) {
        LotDTO lotDTO = lotService.getLotById(id);
        if (lotDTO == null) {
            return ResponseEntity.notFound().build();
        }
        if (lotDTO.getStatus().equals(LotStatus.CREATED)) {
            lotService.actionLot(id);
        }
        if (lotDTO.getStatus().equals(LotStatus.ACTION)) {
            return ResponseEntity.ok().build();
        }
        if (lotDTO.getStatus().equals(LotStatus.SOLD)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{id}/sold")
    public ResponseEntity<Lot> soldLot(@PathVariable Long id) {
        LotDTO lotDTO = lotService.getLotById(id);
        if (lotDTO == null) {
            return ResponseEntity.notFound().build();
        }
        if (lotDTO.getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        if (lotDTO.getStatus().equals(LotStatus.ACTION)) {
            lotService.soldLot(id);
        }
        if (lotDTO.getStatus().equals(LotStatus.SOLD)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/lot")
    public ResponseEntity<FullLotDTO> getFullLot(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(lotService.getFullLot(id));
    }

    @GetMapping
    public ResponseEntity<Collection<LotDTO>> getAllLotsByStatus(@RequestParam LotStatus lotStatus) {
        return ResponseEntity.ok(lotService.getAllLots(lotStatus));
    }
///////////////////////////////////////////Bid/////////////////////////////////////////////////
    @PostMapping("/{id}/bid")
    public ResponseEntity<Bid> createBid(@PathVariable Long id,
                                         @RequestBody BidDTO bidDTO) {
        LotDTO lotDTO = lotService.getLotById(id);
        if (lotDTO == null) {
            return ResponseEntity.notFound().build();
        }
        if (lotDTO.getStatus().equals(LotStatus.CREATED)
                || lotDTO.getStatus().equals(LotStatus.SOLD)) {
            return ResponseEntity.badRequest().build();
        }
        bidDTO.setLotId(id);
        bidService.createBid(bidDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/first")
    public ResponseEntity<BidTarget> getFirstBid(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if(lotService.getLotById(id).getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        BidTarget firstBid = lotService.getFirstBid(id);
        return ResponseEntity.ok(firstBid);
    }

    @GetMapping("/{id}/continual")
    public ResponseEntity<BidTarget> getContinualBid(@PathVariable Long id) {
        if(lotService.getLotById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        if(lotService.getLotById(id).getStatus().equals(LotStatus.CREATED)) {
            return ResponseEntity.badRequest().build();
        }
        BidTarget bid = lotService.getContinualBid(id);
        return ResponseEntity.ok(bid);
    }
//////////////////////////////////////////////CVS/////////////////////////////////////////
@GetMapping("/CSV")
public void getCSVFile(HttpServletResponse response){
    Collection<FullLotDTO> lots = lotService.getAllLotsForCVS();

    response.setContentType("text/csv");
    response.setCharacterEncoding("UTF-8");
    response.setHeader("Content-Disposition", "attachment; filename=\"lots.csv\"");

    try (StringWriter writer = new StringWriter();
         CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
         BufferedWriter output = new BufferedWriter(response.getWriter())) {

        for (FullLotDTO lot : lots) {
            csvPrinter.printRecord(
                    lot.getId(),
                    lot.getLotName(),
                    lot.getStatus(),
                    lot.getLastBid() != null ? lot.getLastBid().getBidName() : "",
                    lot.getCurrentPrice()
            );
        }
        csvPrinter.flush();

        output.write(writer.toString());
    } catch (IOException e) {
        e.printStackTrace();
    }
}


}
