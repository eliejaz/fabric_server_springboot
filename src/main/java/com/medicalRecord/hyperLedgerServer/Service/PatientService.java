package com.medicalRecord.hyperLedgerServer.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.hyperledger.fabric.gateway.Contract;
import org.hyperledger.fabric.gateway.ContractException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.medicalRecord.hyperLedgerServer.Entity.Patient;
import com.medicalRecord.hyperLedgerServer.Util.TransactionUtil;

@Service
public class PatientService {

	@Autowired
	private Contract contract;

	private ObjectMapper mapper = new ObjectMapper();

	public void delete(String id) throws ContractException, TimeoutException, InterruptedException {

		contract.submitTransaction(TransactionUtil.DeletePatient, id);

	}

	public List<Patient> getAll() throws ContractException, TimeoutException, InterruptedException, StreamReadException,
			DatabindException, IOException {

		List<Patient> patients = null;
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.GetAllPatients);
		patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
		});

		return patients;

	}

	public List<Patient> getAllByName() throws ContractException, TimeoutException, InterruptedException,
			StreamReadException, DatabindException, IOException {

		List<Patient> patients = null;
		byte[] data;

		data = contract.submitTransaction(TransactionUtil.ReadAllPatientsByName);
		patients = mapper.readValue(data, new TypeReference<List<Patient>>() {
		});

		return patients;

	}

	public Patient getById(String id) throws ContractException, TimeoutException, InterruptedException,
			StreamReadException, DatabindException, IOException {

		byte[] data;

		data = contract.submitTransaction(TransactionUtil.ReadPatientById, id);
		// list.add(mapper.readValue(data, Patient.class));

		return mapper.readValue(data, Patient.class);

	}

	public void save(Patient patient)
			throws JsonProcessingException, ContractException, TimeoutException, InterruptedException {
		contract.submitTransaction(TransactionUtil.CreatePatient, mapper.writeValueAsString(patient));

	}

	public void update(Patient patient)
			throws JsonProcessingException, ContractException, TimeoutException, InterruptedException {
		contract.submitTransaction(TransactionUtil.UpdatePatient, mapper.writeValueAsString(patient));

	}
}
