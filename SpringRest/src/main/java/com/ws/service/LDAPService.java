package com.ws.service;


public interface LDAPService {

	boolean authenticate(final String userId, final String password);
	
	public <T> String searchUser(final String userId);
}
