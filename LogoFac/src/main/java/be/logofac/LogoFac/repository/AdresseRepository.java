package be.logofac.LogoFac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Patient;

public interface AdresseRepository extends JpaRepository<Adresse, Integer>{

	

}
