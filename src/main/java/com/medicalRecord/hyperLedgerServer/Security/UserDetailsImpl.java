package com.medicalRecord.hyperLedgerServer.Security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.hyperledger.fabric_ca.sdk.Attribute;
import org.hyperledger.fabric_ca.sdk.HFCAIdentity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
 
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;

	public static UserDetailsImpl build(HFCAIdentity user, String username) {
//		List<GrantedAuthority> authorities = user.getAttributes().stream()
//				.map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());

		Collection<GrantedAuthority> authorities =  new ArrayList<>();
		for (Attribute attr :user.getAttributes())
			if (attr.getName().equals("role")) authorities.add(new SimpleGrantedAuthority(attr.getValue()));
		
			return new UserDetailsImpl(user.getEnrollmentId(), username, user.getSecret(), authorities);
	}

	private String id;

	private String username;

	@JsonIgnore
	private String password;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String id, String username, String password,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public String getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
