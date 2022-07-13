package com.medicalRecord.hyperLedgerServer.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.hyperledger.fabric.gateway.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.medicalRecord.hyperLedgerServer.Entity.Patient;
import com.medicalRecord.hyperLedgerServer.Entity.Prescription;
import com.medicalRecord.hyperLedgerServer.Util.TransactionUtil;

@Service
public class PatientService {

	@Autowired
	GatewayService gatewayService;
	private final List<String> bloodTypes =Arrays.asList("A+","B+","A-","B-","AB+","AB-","O+","O-");

	private final ObjectMapper mapper = new ObjectMapper();

	public void delete(String id, String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		contract.submitTransaction(TransactionUtil.DeletePatient, id);

	}

	public List<Patient> getAll(String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		List<Patient> patients = null;
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.GetAllPatients);
		patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
		});

		return patients;

	}

	public List<Patient> getAllByName(String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		List<Patient> patients = null;
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.ReadAllPatientsByName);
		patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
		});

		return patients;

	}
	
	public List<Prescription> getAllPatientPrescription(String patientId , String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		List<Prescription> prescription = null;
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.ReadPatientPerscriptions,patientId);
		prescription = mapper.readValue(data, new TypeReference<List<Prescription>>() {
		});

		return prescription;

	}

	public Patient getById(String id, String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.ReadPatientById, id);
		// list.add(mapper.readValue(data, Patient.class));

		return mapper.readValue(data, Patient.class);

	}

	public void save(Patient patient, String userId) throws Exception {
		if (!bloodTypes.contains(patient.getGroupType()))
				throw new RuntimeException("wrong blood type");
		String uuid="patient"+UUID.randomUUID().toString()+"_user"+userId;
		patient.setId(uuid);
		patient.getDoctorsId().add(userId);
		Contract contract = gatewayService.contract(userId);
		contract.submitTransaction(TransactionUtil.CreatePatient, mapper.writeValueAsString(patient));

	}

	public void update(Patient patient, String userId) throws Exception {
		Contract contract = gatewayService.contract(userId);
		contract.submitTransaction(TransactionUtil.UpdatePatient, mapper.writeValueAsString(patient));

	}
	
	
}
