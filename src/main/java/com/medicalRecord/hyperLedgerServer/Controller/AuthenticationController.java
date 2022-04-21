package com.medicalRecord.hyperLedgerServer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medicalRecord.hyperLedgerServer.Entity.Identity;
import com.medicalRecord.hyperLedgerServer.Security.JwtUtils;
import com.medicalRecord.hyperLedgerServer.Service.FabricCAUserService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;
	@Autowired
	JwtUtils jwtUtils;
	@Autowired
	private  FabricCAUserService fabricCAUserService;
	@GetMapping("/log_in")
	public ResponseEntity<?> login (@RequestBody Identity identity)
	{
		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(identity.getUserId(), identity.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		// UserDetailsImpl userDetails ;
	 
		 authentication.getPrincipal();
		
		String token = jwtUtils.generateJwtToken(authentication);
//	    List<String> roles = userDetails.getAuthorities().stream()
//        .map(item -> item.getAuthority())
//        .collect(Collectors.toList());

		return ResponseEntity.ok(token);
	}
	
	@PostMapping("/sign_up")
	public ResponseEntity<?> signUp (@RequestBody Identity identity) throws Exception
	{
	 
		fabricCAUserService.registerAndEnrollUser(identity.getOrg(), identity.getUserId(), "",identity.getRole(),identity.getPassword());
 
		return ResponseEntity.ok("Registered and Enrolled !");
	}
	@GetMapping("/enrolAdmin")
	public ResponseEntity<?> enrolAdmin () throws Exception
	{
	 
		fabricCAUserService.enrollAdmin("Hosp1MSPc");
 
		return ResponseEntity.ok("Registered and Enrolled !");
	}
}
