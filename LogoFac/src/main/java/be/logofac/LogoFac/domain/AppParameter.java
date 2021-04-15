package be.logofac.LogoFac.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import be.logofac.LogoFac.domain.enums.ParameterType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="paramType", discriminatorType = DiscriminatorType.STRING)
public abstract class AppParameter {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int paramId;
	protected ParameterType paramType;
	public ParameterType getParamType() {
		return paramType;
	}

	public int getParamId() {
		return paramId;
	}
	
	
	
}
