package com.medicalRecord.hyperLedgerServer.Security;

import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private HFCAClient hfcaClient;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		HFCAIdentity user;
		try {
			user = hfcaClient.newHFCAIdentity(username);
			return UserDetailsImpl.build(user ,username);
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
	}

}
