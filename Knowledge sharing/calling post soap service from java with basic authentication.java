package com.test;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//import javax.ws.rs.core.MediaType;
//import org.apache.commons.codec.binary.Base64;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.client.filter.LoggingFilter;
//import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class Chn {
	static
	{
		SSLUtilities.trustAllHostnames();
        SSLUtilities.trustAllHttpsCertificates();
	}

  public static void main(String[] args) {

  try {

    
	  
	// Creating client service
	Client client = Client.create();
    
    client.addFilter(new LoggingFilter());
    //Enter Authentication details
    client.addFilter( new HTTPBasicAuthFilter("hybrisuser","hybris2016"));
    //Enter Instance details about call back services
    WebResource webResource = client
       .resource("https://10.64.168.114:9005/vodafonegenericintegration/vetting/");
    
  //Converting xml file to a string
    String finalstr="";
    try 
    		
	{
    	//XML path reader
    	BufferedReader br = new BufferedReader(new FileReader("C:\\Srinadha\\XMLS\\CVC.XML"));
		String sCurrentLine;
		while ((sCurrentLine = br.readLine()) != null) {
			finalstr=finalstr+sCurrentLine;
		}
		System.out.println("finalstr +"+ finalstr);

	} catch (IOException e) {
		e.printStackTrace();
	}
   
   //Posting response back to server.
    
    ClientResponse response=webResource.type("text/xml").post(ClientResponse.class,finalstr);

   //if call back service throwing other than 200 status code
    
    if (response.getStatus() != 200) {
      throw new RuntimeException("Failed : HTTP error code : "
           + response.getStatus());
    }

    System.out.println("Output from Server .... \n");
    String output = response.getEntity(String.class);
    System.out.println(output);

    } catch (Exception e) {

    e.printStackTrace();

    }
 

  }
 
}