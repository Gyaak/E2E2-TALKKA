package com.talkka.server.bus.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusPlateStatisticRepository extends JpaRepository<BusPlateStatisticEntity, Long> {
	Optional<BusPlateStatisticEntity> findFirstByPlateNo(String plateNo);
}
