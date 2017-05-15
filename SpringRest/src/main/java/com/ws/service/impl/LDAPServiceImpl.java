package com.ws.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.AuthenticationException;
import org.springframework.ldap.NameNotFoundException;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.LdapContextSource;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;

import com.ws.service.LDAPService;

public class LDAPServiceImpl implements LDAPService {

	@Autowired(required = true)
	private LdapTemplate ldapTemplate;

	@Autowired(required = true)
	private LdapContextSource contextSource;

	public boolean authenticate(final String userId, final String password) {

		AndFilter filter = new AndFilter();

		// Set ldap server setting.
		this.setContext("ldap://localhost:10389", "uid=admin,ou=system",
				"secret");
		// Apply the filter.
		filter.and(new EqualsFilter("uid", userId));
		String filterPath = "ou=Users,dc=example,dc=com";
		// Authenticate the user credentials.

		boolean isAuthenticated = false;
		try {
			isAuthenticated = this.ldapTemplate.authenticate(filterPath,
					filter.toString(), password);
		} catch (Exception e) {
			System.out.println(e);
		}
		if (isAuthenticated) {
			return true;
		}

		return false;
	}

	private void setContext(final String serviceUrl, final String user,
			final String password) {
		this.contextSource.setUrl(serviceUrl);
		if (null != user) {
			this.contextSource.setUserDn(user);
		}
		if (null != password) {
			this.contextSource.setPassword(password);
		}
		this.contextSource.afterPropertiesSet();
		this.ldapTemplate.setContextSource(this.contextSource);
	}
	
	@SuppressWarnings("unchecked")
	public <T> String searchUser(final String userId)
			throws NameNotFoundException, IndexOutOfBoundsException,
			AuthenticationException {

		this.setContext("ldap://localhost:10389", null, null);
		final String filterMatch = "uid";
		final String filterPath = "ou=Users,dc=example,dc=com";

		final AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter(filterMatch, userId));
		List<T> list = new ArrayList<T>();
		list = this.ldapTemplate.search(filterPath, filter.encode(),
				(AttributesMapper) attrs -> {
					final List list1 = new ArrayList();
					list1.add(attrs.get("employeeNumber"));
					list1.add(attrs.get("givenName"));
					return list1;
				});
		final String[] string = list.get(0).toString().split(",");
		for (String string2 : string) {
			System.out.println("-------------" + string2);
		}
		
		return "Hai";
	}

}
