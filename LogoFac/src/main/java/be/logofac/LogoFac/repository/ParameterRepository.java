package be.logofac.LogoFac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.AppParameter;
import be.logofac.LogoFac.domain.AppParameterAmount;
import be.logofac.LogoFac.domain.AppParameterGeneral;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.domain.enums.ParameterReference;

public interface ParameterRepository extends JpaRepository<AppParameter, Integer>{

	@Query("SELECT A FROM AppParameterAmount A ")
	List<AppParameterAmount> findAllAmounts();
	@Query("SELECT A FROM AppParameterGeneral A ")
	List<AppParameterGeneral> findAllGeneral();
	
	AppParameterGeneral findByParameterReference(ParameterReference folderLocation);

	
	
	

}
