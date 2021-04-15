package be.logofac.LogoFac.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer>{

	@Query("SELECT COUNT(F) FROM Facture F WHERE applicationDate  >= :beginDate AND applicationDate  <= :endDate")
	int countInTime(@Param("beginDate") LocalDate beginDate, @Param("endDate")LocalDate endDate);
	
	@Query("SELECT COUNT(F) FROM Facture F join  F.seances S  WHERE S.seanceId = :id ")
	int countSeance(@Param("id")int seanceId);

}
