package be.logofac.LogoFac.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Professionnel;
@Repository
public interface ProfessionnelRepository extends JpaRepository<Professionnel, Integer>{

	@Query("SELECT P FROM Professionnel P JOIN FETCH P.adresse")
	List<Professionnel> findAllAndAdress();
}
