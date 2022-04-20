package com.medicalRecord.hyperLedgerServer.Entity;

import lombok.Data;

@Data
public class Identity {

    private String org;
    private String affiliation;
    private String userId;
    private String role;
    private String password;
}
