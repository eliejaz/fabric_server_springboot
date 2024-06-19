package com.medicalRecord.hyperLedgerServer.Security;

import java.io.IOException;

import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.hyperledger.fabric_ca.sdk.exception.IdentityException;
import org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.medicalRecord.hyperLedgerServer.Service.FabricCAUserService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private FabricCAUserService fabricCAUserService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 
		HFCAIdentity user;
		try {
			 user = fabricCAUserService.getIdentity(username);
			return UserDetailsImpl.build(user ,username);
		} catch (InvalidArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IdentityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
	}

}
