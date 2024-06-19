package com.medicalRecord.hyperLedgerServer.Service;

import java.util.UUID;

import org.hyperledger.fabric.gateway.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.medicalRecord.hyperLedgerServer.Entity.Diagnosis;
import com.medicalRecord.hyperLedgerServer.Util.TransactionUtil;

@Service
public class DiagnosisService {
	@Autowired
	GatewayService gatewayService;
	
	private final ObjectMapper mapper = new ObjectMapper();

	
//	public void delete(String id, String userId) throws Exception {
//		Contract contract = gatewayService.contract(userId);
//		contract.submitTransaction(TransactionUtil.DeleteDiagnosis, id);
//
//	}

//	public List<Patient> getAll(String userId , String patientId) throws Exception {
//		Contract contract = gatewayService.contract(userId);
//		List<Patient> patients = null;
//		byte[] data;
//
//		data = contract.submitTransaction(TransactionUtil.);
//		patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
//		});
//
//		return patients;
//
//	}
 
//	public Patient getById(String id, String userId) throws Exception {
//		Contract contract = gatewayService.contract(userId);
//		byte[] data;
//
//		data = contract.submitTransaction(TransactionUtil., id);
//		// list.add(mapper.readValue(data, Patient.class));
//
//		return mapper.readValue(data, Patient.class);
//
//	}

	public void save(Diagnosis diagnosis, String patientId, String userId) throws Exception {
		String uuid="diagnosis"+UUID.randomUUID().toString()+"_user"+userId;
		diagnosis.setId(uuid);
		Contract contract = gatewayService.contract(userId);
		contract.submitTransaction(TransactionUtil.CreateDiagnosis, mapper.writeValueAsString(diagnosis) , patientId);

	}

	public void update(Diagnosis diagnosis, String patientId, String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		contract.submitTransaction(TransactionUtil.UpdateDiagnosis, mapper.writeValueAsString(diagnosis),patientId);

	}
}
