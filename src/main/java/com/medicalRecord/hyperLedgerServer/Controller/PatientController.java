package com.medicalRecord.hyperLedgerServer.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import com.medicalRecord.hyperLedgerServer.Security.UserDetailsImpl;
import com.medicalRecord.hyperLedgerServer.Service.PatientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/patient")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient, @AuthenticationPrincipal UserDetailsImpl user)
			throws Exception {
		patientService.save(patient, user.getId());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/patient")
	public ResponseEntity<?> updatePatient(@RequestBody Patient patient, @AuthenticationPrincipal UserDetailsImpl user)
			throws Exception {
		patientService.update(patient, user.getId());
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/patient")
	public ResponseEntity<?> deletePatient(@RequestBody String patient_id,
			@AuthenticationPrincipal UserDetailsImpl user) throws Exception {
		patientService.delete(patient_id, user.getId());
		return ResponseEntity.ok().build();
	}

	@GetMapping("/patients")
	public ResponseEntity<?> getPatients(@AuthenticationPrincipal UserDetailsImpl user) throws Exception {
		List<Patient> patients = patientService.getAll(user.getId());
		return ResponseEntity.ok(patients);
	}

	@GetMapping("/patient_prescription")
	public ResponseEntity<?> getPatientPrescription(String patient_id, @AuthenticationPrincipal UserDetailsImpl user)
			throws Exception {
		List<Prescription> prescription = patientService.getAllPatientPrescription(patient_id, user.getId());
		return ResponseEntity.ok(prescription);
	}

	@GetMapping("/patient")
	public ResponseEntity<?> getPatient(@RequestBody String patient_id, @AuthenticationPrincipal UserDetailsImpl user)
			throws Exception {
		Patient patient = patientService.getById(patient_id, user.getId());
		return ResponseEntity.ok(patient);
	}

}
