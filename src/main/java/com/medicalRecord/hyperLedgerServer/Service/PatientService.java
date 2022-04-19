package com.medicalRecord.hyperLedgerServer.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.medicalRecord.hyperLedgerServer.Entity.Patient;
import com.medicalRecord.hyperLedgerServer.Util.TransactionUtil;

@Service
public class PatientService {

	@Autowired
	private Contract contract;

	private ObjectMapper mapper = new ObjectMapper();

	public void delete(String id) {
	
			try {
				contract.submitTransaction(TransactionUtil.DeletePatient, id);
			} catch (ContractException | TimeoutException | InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public List<Patient> getAll() {

		List<Patient> patients = null;
		byte[] data;
		try {
			data = contract.submitTransaction(TransactionUtil.GetAllPatients);
			patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
			});

		} catch (ContractException | TimeoutException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patients;

	}
	public List<Patient> getAllByName() {

		List<Patient> patients = null;
		byte[] data;
		try {
			data = contract.submitTransaction(TransactionUtil.ReadAllPatientsByName);
			patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
			});

		} catch (ContractException | TimeoutException | InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return patients;

	}


	public Patient getById(String id) {
 
		byte[] data;
		try {
			data = contract.submitTransaction(TransactionUtil.ReadPatientById, id);
			//list.add(mapper.readValue(data, Patient.class));
			return mapper.readValue(data, Patient.class);

		} catch (ContractException | TimeoutException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public void save(Patient patient) {
		try {
			contract.submitTransaction(TransactionUtil.CreatePatient, mapper.writeValueAsString(patient));
		} catch (JsonProcessingException | ContractException | TimeoutException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void update(Patient patient) {

		try {
			contract.submitTransaction(TransactionUtil.UpdatePatient, mapper.writeValueAsString(patient));
		} catch (JsonProcessingException | ContractException | TimeoutException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
