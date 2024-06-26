package com.medicalRecord.hyperLedgerServer.Security;

import java.util.List;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private int id;
	private String username;
	private List<String> roles;

	public JwtResponse(String accessToken, int id, String username, List<String> roles) {
		this.token = accessToken;
		this.id = id;
		this.username = username;

		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public int getId() {
		return id;
	}

	public List<String> getRoles() {
		return roles;
	}

	public String getTokenType() {
		return type;
	}

	public String getUsername() {
		return username;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
