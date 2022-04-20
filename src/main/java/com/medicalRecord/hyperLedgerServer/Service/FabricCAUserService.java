package com.medicalRecord.hyperLedgerServer.Service;

import lombok.extern.slf4j.Slf4j;

import org.hyperledger.fabric.gateway.Identities;
import org.hyperledger.fabric.gateway.Identity;
import org.hyperledger.fabric.gateway.Wallet;
import org.hyperledger.fabric.gateway.X509Identity;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric_ca.sdk.Attribute;
import org.hyperledger.fabric_ca.sdk.EnrollmentRequest;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.RegistrationRequest;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.hyperledger.fabric_ca.sdk.exception.IdentityException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.medicalRecord.hyperLedgerServer.Entity.FabricCAUser;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.Collection;

@Slf4j
public class FabricCAUserService {

    private  HFCAClient hfcaClient;
    private  Wallet wallet;
    private  String adminUserId;
    private  String adminUserPassword;
    @Autowired
    PasswordEncoder passwordEncoder;
    public FabricCAUserService(HFCAClient hfcaClient, Wallet wallet, String adminUserId, String adminUserPassword) {
        this.hfcaClient = hfcaClient;
        this.wallet = wallet;
        this.adminUserId = adminUserId;
        this.adminUserPassword = adminUserPassword;
    }

    public FabricCAUserService() {

    }


    public void enrollAdmin(String orgMspId) throws IOException, InvalidArgumentException,
            EnrollmentException, CertificateException {
        if (wallet.get(adminUserId) != null) {
            log.warn(String.format("An identity for the admin user \"%s\" already exists in the wallet", adminUserId));
            return;
        }
        final EnrollmentRequest enrollmentRequestTLS = new EnrollmentRequest();
        enrollmentRequestTLS.setProfile("tls");
        Enrollment enrollment = hfcaClient.enroll(adminUserId, adminUserPassword, enrollmentRequestTLS);
        Identity identity = Identities.newX509Identity(orgMspId, enrollment);
        wallet.put(adminUserId, identity);
        log.info(String.format("Successfully enrolled user \"%s\" and imported it into the wallet", adminUserId));
    }
    public void registerAndEnrollUser(String orgMspId, String userId, String affiliation) throws Exception {
        if (wallet.get(userId) != null) {
            log.warn(String.format("An identity for the user \"%s\" already exists in the wallet", userId));
            return;
        }
        X509Identity adminIdentity = (X509Identity) wallet.get(adminUserId);
        if (adminIdentity == null) {
            log.warn(String.format("\"%s\" needs to be enrolled and added to the wallet first", adminUserId));
            return;
        }
         FabricCAUser admin = FabricCAUser.builder()
                .userId(adminUserId)
                .orgMSP(orgMspId)
                .affiliation(affiliation)
                .identity(adminIdentity)
                .build();
        // Register the user, enroll the user, and import the new identity into the wallet.
        
        Enrollment enrollment = getEnrollment(admin, userId);
        Identity user = Identities.newX509Identity(orgMspId, enrollment);
         wallet.put(userId, user);
         
        log.info(String.format("Successfully enrolled user \"%s\" and imported it into the wallet%n", userId));
    }


    public void registerAndEnrollUser(String orgMspId, String userId, String affiliation ,String role ,String secret) throws Exception {
        if (wallet.get(userId) != null) {
            log.warn(String.format("An identity for the user \"%s\" already exists in the wallet", userId));
            return;
        }
        X509Identity adminIdentity = (X509Identity) wallet.get(adminUserId);
        if (adminIdentity == null) {
            log.warn(String.format("\"%s\" needs to be enrolled and added to the wallet first", adminUserId));
            return;
        }
         FabricCAUser admin = FabricCAUser.builder()
                .userId(adminUserId)
                .orgMSP(orgMspId)
                .affiliation(affiliation)
                .identity(adminIdentity)
                .build();
        // Register the user, enroll the user, and import the new identity into the wallet.
        
        Enrollment enrollment = getEnrollment(admin, userId , role , secret);
        Identity user = Identities.newX509Identity(orgMspId, enrollment);
         wallet.put(userId, user);
         
        log.info(String.format("Successfully enrolled user \"%s\" and imported it into the wallet%n", userId));
    }

 
    public Boolean userExist(String userId) throws IOException {
        return wallet.get(userId) != null;
    }

    private Enrollment getEnrollment(FabricCAUser admin, String userId ) throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest(userId);
        registrationRequest.setAffiliation(admin.getAffiliation());
        registrationRequest.setEnrollmentID(userId);
     
        String enrollmentSecret = hfcaClient.register(registrationRequest, admin);
        return hfcaClient.enroll(userId, enrollmentSecret);
    
        
        //hfcaClient.
    }
    private Enrollment getEnrollment(FabricCAUser admin, String userId ,String role , String secret) throws Exception {
        RegistrationRequest registrationRequest = new RegistrationRequest(userId);
        registrationRequest.setAffiliation(admin.getAffiliation());
        registrationRequest.setEnrollmentID(userId);
        registrationRequest.getAttributes().add(new Attribute ("role",role));
        registrationRequest.setSecret(passwordEncoder.encode(secret));
        
         
        String enrollmentSecret = hfcaClient.register(registrationRequest, admin);
        return hfcaClient.enroll(userId, enrollmentSecret);
    
        
        //hfcaClient.
    }
    
    public Collection<HFCAIdentity> getAllIdentities(FabricCAUser admin) throws IdentityException, InvalidArgumentException
    {
    
        	return  hfcaClient.getHFCAIdentities(admin);
 
 

    }
    
    
}
