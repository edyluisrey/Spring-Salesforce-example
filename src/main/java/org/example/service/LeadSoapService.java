package org.example.service;

import com.sforce.soap.enterprise.sobject.Lead;
import com.sforce.ws.ConnectionException;

public interface LeadSoapService {
	public void ListLeads() throws ConnectionException;
	public  void createLeads(Lead[] records);
}
