package be.logofac.LogoFac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Patient;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	@Query("SELECT P FROM Patient P JOIN FETCH P.adresse")
	List<Patient> findAllAndAdresse();
	
	@Query("SELECT P FROM Patient P join fetch P.adresse  WHERE P.patientId = :id")
	Patient fetchByid(@Param("id") int patientId);


}
