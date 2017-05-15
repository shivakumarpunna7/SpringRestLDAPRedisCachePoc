package com.ws.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ws.dao.Login;
import com.ws.dao.Service;
import com.ws.service.LDAPService;
import com.ws.service.TestService;

@RestController
@RequestMapping("/login")
public class TestController {
	
	@Autowired
	TestService testService;
	
	@Autowired
	LDAPService lDAPService;

	@RequestMapping(value = "/validate/", method = RequestMethod.POST)
	public final Service login(@RequestBody final Login login) {
		System.out.println("*******************************************");
		Service service = new Service();
		service.setMessage("You have logged in successfully!!!");
		service.setStatus("SUCCESS");

		return service;
	}

	@RequestMapping(value = "/test/", method = RequestMethod.GET)
	public final String test() {

		Service service = new Service();
		service.setMessage("You have logged in successfully!!!");
		service.setStatus("SUCCESS");

		return "hai";
	}

	@RequestMapping(value = "/cache/{userId}/", method = RequestMethod.GET)
	public final List<Login> testCache(@PathVariable("userId") final String userId) {
		System.out.println("*******************************************" + userId);
		return testService.userAccounts(userId);
	}
	

	@RequestMapping(value = "/validateCredentials/", method = RequestMethod.POST)
	public final Service loginValidation(@RequestBody final Login login) {
		System.out.println("*******************************************");
		
		boolean status = lDAPService.authenticate(login.getUserName(), login.getPassword());
		
		lDAPService.searchUser(login.getUserName());
		Service service = new Service();
		service.setMessage("You have logged in successfully!!!");
		service.setStatus("=====" + status);

		return service;
	}

	
}
