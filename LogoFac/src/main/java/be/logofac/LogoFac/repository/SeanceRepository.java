package be.logofac.LogoFac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Seance;
@Repository

public interface SeanceRepository extends JpaRepository<Seance, Integer>{

	@Query("SELECT S FROM Seance S INNER JOIN FETCH S.patient P INNER JOIN FETCH S.professionnel Pro INNER JOIN FETCH P.adresse INNER JOIN FETCH Pro.adresse  ")
	List<Seance> findAllAndPatient();

	@Query("SELECT S FROM Seance S INNER JOIN FETCH S.patient P INNER JOIN FETCH S.professionnel Pro INNER JOIN FETCH P.adresse INNER JOIN FETCH Pro.adresse WHERE S.seanceId = :id")
	Seance fetchById(@Param("id")  int seanceId);
	
	
	@Query("SELECT S FROM Seance S INNER JOIN FETCH S.patient P INNER JOIN FETCH S.professionnel Pro INNER JOIN FETCH P.adresse INNER JOIN FETCH Pro.adresse WHERE P.patientId = :id")
	List<Seance> fetchByPatientId(@Param("id")  int seanceId);
	
	

}
