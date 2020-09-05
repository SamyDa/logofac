package be.logofac.LogoFac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Facture;
@Repository
public interface FactureRepository extends JpaRepository<Facture, Integer>{

}
