package com.example.kexkex.repository;

import com.example.kexkex.lotEnum.LotStatus;
import com.example.kexkex.model.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long> {

    Collection<Lot> findAllByStatus(LotStatus lotStatus);
}

