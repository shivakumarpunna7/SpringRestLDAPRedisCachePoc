package com.ws.service;

import java.util.List;

import com.ws.dao.Login;

public interface TestService {

	public List<Login> userAccounts(String userId);
}
