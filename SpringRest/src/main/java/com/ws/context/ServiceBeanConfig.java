package com.ws.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ws.service.LDAPService;
import com.ws.service.TestService;
import com.ws.service.TestserviceImpl;
import com.ws.service.impl.LDAPServiceImpl;

@Configuration
public class ServiceBeanConfig {

	@Bean(name = "TestService")
    public TestService testService() {
        return new TestserviceImpl();
    }
	
	@Bean(name = "LDAPService")
    public LDAPService lDAPService() {
        return new LDAPServiceImpl();
    }
}
