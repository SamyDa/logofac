package be.logofac.LogoFac.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import be.logofac.LogoFac.domain.enums.ParameterReference;
import be.logofac.LogoFac.domain.enums.ParameterType;

@Entity
public class AppParameterGeneral extends AppParameter {

//	@Column(unique =  true)
	private ParameterReference parameterReference;
	private String value;
	
	public AppParameterGeneral() {
		
		this.paramType = ParameterType.General;
	}

	public ParameterReference getParameterReference() {
		return parameterReference;
	}

	public void setParameterReference(ParameterReference parameterReference) {
		this.parameterReference = parameterReference;
	}

	public Object getValue() {
		if(parameterReference.getInstance() instanceof Integer) {
			return Integer.valueOf(value);
		}
		return value;
	}

	public void setValue(Object value) {
		
		if(parameterReference == null)
			 return;
		
		if(value.getClass().isInstance( this.parameterReference.getInstance())) {
			this.value = String.valueOf(value);
		}
		
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
