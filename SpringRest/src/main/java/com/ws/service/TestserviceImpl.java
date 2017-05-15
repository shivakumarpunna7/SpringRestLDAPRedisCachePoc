package com.ws.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.ws.dao.Login;

public class TestserviceImpl implements TestService {

	@Cacheable(value = "userProfile", key = "#userId")
	public final List<Login> userAccounts(String userId) {

		System.out.println("*******************************************");
		List<Login> credentials = new ArrayList<Login>();
		Login login1 = new Login();
		login1.setUserName("shivaKumar");
		login1.setPassword("shiva");
		credentials.add(login1);

		Login login2 = new Login();
		login2.setUserName("NaniPunna");
		login2.setPassword("nani");
		credentials.add(login2);

		Login login3 = new Login();
		login3.setUserName("Nagendrababu");
		login3.setPassword("babu");
		credentials.add(login3);

		Login login5 = new Login();
		login5.setUserName("umadevi");
		login5.setPassword("uma");
		credentials.add(login5);

		Login login4 = new Login();
		login4.setUserName("Swathikappla");
		login4.setPassword("swathi");
		credentials.add(login4);

		Login login6 = new Login();
		login6.setUserName("Tahiseen");
		login6.setPassword("soni");
		credentials.add(login6);

		return credentials;
	}
	
}
