package org.example.service;

import org.springframework.stereotype.Service;

import com.sforce.soap.enterprise.Connector;
import com.sforce.soap.enterprise.EnterpriseConnection;
import com.sforce.soap.enterprise.QueryResult;
import com.sforce.soap.enterprise.SaveResult;
import com.sforce.soap.enterprise.sobject.Lead;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

@Service
public class LeadSoapServiceImpl implements LeadSoapService  {
  
	private EnterpriseConnection getConection() throws ConnectionException{
		ConnectorConfig config = new ConnectorConfig();
	    config.setUsername("eaguirre378+3@gmail.com");
	    config.setPassword("taller123457xLPHbDPqHweItCwDdQgTFiBF");
	    EnterpriseConnection connection = Connector.newConnection(config);
	    return connection;
	}
	
	@Override
	public  void ListLeads() throws ConnectionException {	  
		QueryResult queryResults = getConection().query("SELECT Id, FirstName, LastName, Company FROM Lead ORDER BY CreatedDate DESC LIMIT 5");
        if (queryResults.getSize() > 0) {
          for (int i=0;i<5;i++){
            Lead l = (Lead)queryResults.getRecords()[i];
            System.out.println("Id: " + l.getId() + " - Name: "+ l.getFirstName()+" "+ l.getLastName()+" - Company: "+l.getCompany());
          }
        } 
	}
	
	@Override
    public  void createLeads(Lead[] records) {       
      try {        
        SaveResult[] saveResults = getConection().create(records);
        checkLeadsCreated(saveResults);  
      } catch (Exception e) {
        e.printStackTrace();
      }    
  
    }
    
    private   void checkLeadsCreated(SaveResult[] saveResults){
    	 for (int i=0; i< saveResults.length; i++) {
             if (saveResults[i].isSuccess()) {
               System.out.println(i+". Successfully created record - Id: " + saveResults[i].getId());
             } else {
               com.sforce.soap.enterprise.Error[] errors = saveResults[i].getErrors();
               for (int j=0; j< errors.length; j++) {
                 System.out.println("ERROR creating record: " + errors[j].getMessage());
               }
             }
           }	
    }
}
