package com.medicalRecord.hyperLedgerServer.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalRecord.hyperLedgerServer.Entity.Patient;
import com.medicalRecord.hyperLedgerServer.Entity.Prescription;
import com.medicalRecord.hyperLedgerServer.Service.PatientService;

import lombok.extern.slf4j.Slf4j;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/patient")
@Slf4j
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/patient")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient,  Principal user)
			throws Exception {
		patientService.save(patient, user.getName());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/patient")
	public ResponseEntity<?> updatePatient(@RequestBody Patient patient,  Principal user)
			throws Exception {
		patientService.update(patient, user.getName());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/patient")
	public ResponseEntity<?> deletePatient(@RequestBody String patient_id, Principal user) throws Exception {
		patientService.delete(patient_id, user.getName());
		return ResponseEntity.ok().build();
	}

	@GetMapping(value = "/patients" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPatients( Principal user) throws Exception {
		log.warn("USER : " + user.getName());
		List<Patient> patients = patientService.getAll(user.getName());
		return ResponseEntity.ok(patients);
	}

	@GetMapping(value = "/patient_prescription" ,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPatientPrescription(String patient_id,  Principal user)
			throws Exception {
		List<Prescription> prescription = patientService.getAllPatientPrescription(patient_id, user.getName());
		return ResponseEntity.ok(prescription);
	}

	@GetMapping(value = "/patient",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getPatient(@RequestBody String patient_id,  Principal user)
			throws Exception {
		Patient patient = patientService.getById(patient_id, user.getName());
		return ResponseEntity.ok(patient);
	}

}
