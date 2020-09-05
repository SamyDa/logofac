package be.logofac.LogoFac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import be.logofac.LogoFac.domain.Prix;
@Repository
public interface PrixRepository extends JpaRepository<Prix, Integer>{

}
