package org.example.app;

import org.example.service.LeadSoapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sforce.soap.enterprise.sobject.Lead;
import com.sforce.ws.ConnectionException;


@Controller
public class LeadSoapController {
	@Autowired  
	LeadSoapService leadSoapService;
	 	 
	 @RequestMapping(value = {"/","/lead/list" }, method = RequestMethod.GET)
	 public String listLead(Model model) {
		   model.addAttribute("title", "Welcome salesforce SOAP API test");
		   try {
			   leadSoapService.ListLeads();
		   } catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	       return "index";
	 }
	 
	 @RequestMapping(value= "/Lead/add", method = RequestMethod.GET)
	 public String addLead(Model model){
		 Lead[] records = new Lead[2]; 
		 for (int i=0;i<2;i++) {
	          Lead l = new Lead();
	          l.setFirstName("SOAP API");
	          l.setLastName("Lead test"+i);
	          l.setCompany("Test company");  
	          records[i] = l;
	        }
		 leadSoapService.createLeads(records);
		 return "index";
	 }
	 
	 @RequestMapping(value = {"/callback" }, method = RequestMethod.GET)
	 public String callback(Model model) {
		   model.addAttribute("title", "Welcome salesforce SOAP API test");		   
	       return "index";
	 }
}
