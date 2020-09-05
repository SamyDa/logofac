package be.logofac.LogoFac.service;

import java.util.List;

import org.springframework.stereotype.Service;

import be.logofac.LogoFac.domain.Adresse;
import be.logofac.LogoFac.domain.Facture;
import be.logofac.LogoFac.domain.Patient;
import be.logofac.LogoFac.repository.PatientRepository;

@Service
public class PatientService {
	
	private PatientRepository patientRepository;
	private AdresseService adresseService;
	
	
	
	public PatientService(PatientRepository patientRepository, AdresseService adresseService) {
		super();
		this.patientRepository = patientRepository;
		this.adresseService = adresseService;
	}



	public void save(Patient patient) {
		adresseService.save(patient.getAdresse());
		patientRepository.save(patient);
		
	}



	public List<Patient> findAll() {
		return patientRepository.findAllAndAdresse();
	}



	public void fetchLazy(Patient patient) {
		Patient fetchedPatient  = patientRepository.fetchByid(patient.getPatientId());
		patient.setAdresse(fetchedPatient.getAdresse());
	}
	
	

}
