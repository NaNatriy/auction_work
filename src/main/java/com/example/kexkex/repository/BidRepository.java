package com.example.kexkex.repository;

import com.example.kexkex.model.Bid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BidRepository extends JpaRepository<Bid, Long> {

    @Query(value = "SELECT COUNT(*) FROM bid WHERE lot_id = ?1", nativeQuery = true)
    Long bidCount(Long id);

    @Query(value = "SELECT bid_name AS bidName, bid_time AS bidTime FROM bid AS b WHERE b.lot_id = ?1 ORDER BY bid_time LIMIT 1", nativeQuery = true)
    BidTarget getFirstBid(Long id);

    @Query(value = "SELECT bid_name AS bidName, bid_time AS bidTime FROM bid WHERE lot_id = ?1 ORDER BY bid_time DESC LIMIT 1", nativeQuery = true)
    BidTarget getLastBid(Long id);

    @Query(value = "SELECT bid_name AS bidName, MAX(b.bids) AS max_bids, MAX(last_bid_time) AS bidTime " +
            "FROM (SELECT bid_name, COUNT(*) AS bids, MAX(bid_time) AS last_bid_time " +
            "FROM bid WHERE lot_id = ?1 GROUP BY bid_name) AS b " +
            "GROUP BY bid_name ORDER BY max_bids DESC LIMIT 1", nativeQuery = true)
    BidTarget getContinualBid(Long id);
}
