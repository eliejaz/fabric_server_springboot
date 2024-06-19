package com.medicalRecord.hyperLedgerServer.Controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medicalRecord.hyperLedgerServer.Entity.Diagnosis;
import com.medicalRecord.hyperLedgerServer.Service.DiagnosisService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/diagnosis")
public class DiagnosisController {
	@Autowired
	DiagnosisService diagnosisService;
	@PostMapping("/diagnosis")
	public ResponseEntity<?> createDiagnosis(@RequestBody Diagnosis diagnosis, @RequestParam String patientId ,  Principal user) throws Exception
	{
		diagnosisService.save(diagnosis, patientId, user.getName());
		return ResponseEntity.ok().build();
	}
	@PutMapping("/diagnosis")
	public ResponseEntity<?> updateDiagnosis(@RequestBody Diagnosis diagnosis , @RequestParam String patientId, Principal user) throws Exception
	{
		diagnosisService.update(diagnosis, patientId, user.getName());
		return ResponseEntity.ok().build();
	}
	
//	@DeleteMapping("/diagnosis")
//	public ResponseEntity<?> deleteDiagnosis(@RequestBody int diagnosis_id)
//	{
//		return null;
//	}
//	@GetMapping("/diagnosiss")
//	public ResponseEntity<?> getDiagnosiss()
//	{
//		return null;
//	}
//	
//	@GetMapping("/diagnosis")
//	public ResponseEntity<?> getDiagnosis(@RequestBody int diagnosis_id)
//	{
//		return null;
//	}
//	
}
