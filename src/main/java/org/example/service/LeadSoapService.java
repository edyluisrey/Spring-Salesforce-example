package org.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.sobject.Lead;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

@Service
public class LeadSoapService {
  
	private static EnterpriseConnection getConection() throws ConnectionException{
		ConnectorConfig config = new ConnectorConfig();
	    config.setUsername("username");
	    config.setPassword("PasswordToken");
	    EnterpriseConnection connection = Connector.newConnection(config);
	    return connection;
	}
	
	public static void ListLeads() throws ConnectionException {	  
		QueryResult queryResults = getConection().query("SELECT Id, FirstName, LastName, Company FROM Lead ORDER BY CreatedDate DESC LIMIT 5");
        if (queryResults.getSize() > 0) {
          for (int i=0;i<5;i++){
            Lead l = (Lead)queryResults.getRecords()[i];
            System.out.println("Id: " + l.getId() + " - Name: "+ l.getFirstName()+" "+ l.getLastName()+" - Company: "+l.getCompany());
          }
        } 
	}
}
