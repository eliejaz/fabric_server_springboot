package com.medicalRecord.hyperLedgerServer.Controller;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.ContractException;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.medicalRecord.hyperLedgerServer.Entity.Patient;
import com.medicalRecord.hyperLedgerServer.Security.UserDetailsImpl;
import com.medicalRecord.hyperLedgerServer.Service.PatientService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/patient")
public class PatientController {
	@Autowired
	PatientService patientService;

	@PostMapping("/patient")
	public ResponseEntity<?> createPatient(@RequestBody Patient patient ,@AuthenticationPrincipal UserDetailsImpl user)
			throws JsonProcessingException, ContractException, TimeoutException, InterruptedException {
		patientService.save(patient);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/patient")
	public ResponseEntity<?> updatePatient(@RequestBody Patient patient,@AuthenticationPrincipal UserDetailsImpl user)
			throws JsonProcessingException, ContractException, TimeoutException, InterruptedException {
		patientService.update(patient);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/patient")
	public ResponseEntity<?> deletePatient(@RequestBody String patient_id,@AuthenticationPrincipal UserDetailsImpl user)
			throws ContractException, TimeoutException, InterruptedException {
		patientService.delete(patient_id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/patients")
	public ResponseEntity<?> getPatients(@AuthenticationPrincipal UserDetailsImpl user) throws StreamReadException, DatabindException, ContractException,
			TimeoutException, InterruptedException, IOException {
		List<Patient> patients = patientService.getAll();
		return ResponseEntity.ok(patients);
	}

	@GetMapping("/patient")
	public ResponseEntity<?> getPatient(@RequestBody String patient_id ,@AuthenticationPrincipal UserDetailsImpl user) throws StreamReadException, DatabindException,
			ContractException, TimeoutException, InterruptedException, IOException {
		Patient patient = patientService.getById(patient_id);
		return ResponseEntity.ok(patient);
	}

}
