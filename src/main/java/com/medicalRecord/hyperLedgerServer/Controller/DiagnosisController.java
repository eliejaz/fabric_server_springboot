package com.medicalRecord.hyperLedgerServer.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalRecord.hyperLedgerServer.Entity.Diagnosis;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/diagnosis")
public class DiagnosisController {
	@PostMapping("/diagnosis")
	public ResponseEntity<?> createDiagnosis(@RequestBody Diagnosis diagnosis)
	{
		return null;
	}
	@PutMapping("/diagnosis")
	public ResponseEntity<?> updateDiagnosis(@RequestBody Diagnosis diagnosis)
	{
		return null;
	}
	
	@DeleteMapping("/diagnosis")
	public ResponseEntity<?> deleteDiagnosis(@RequestBody int diagnosis_id)
	{
		return null;
	}
	@GetMapping("/diagnosiss")
	public ResponseEntity<?> getDiagnosiss()
	{
		return null;
	}
	
	@GetMapping("/diagnosis")
	public ResponseEntity<?> getDiagnosis(@RequestBody int diagnosis_id)
	{
		return null;
	}
	
}
