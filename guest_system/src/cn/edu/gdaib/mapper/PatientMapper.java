package cn.edu.gdaib.mapper;

import java.util.List;

import cn.edu.gdaib.domain.Patient;

public interface PatientMapper {

	List<Patient> getPatientList();
	void insertPatient(Patient patient);
	List<Patient> getPatientByName(String name);
	void updatePatientById(Patient patient);
	void delectPatientById(long id);
}
