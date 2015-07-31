package org.example.app;

import org.example.service.LeadSoapService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sforce.ws.ConnectionException;

@Controller
public class LeadSoapController {
	 
	 @RequestMapping(value = { "/lead/list" }, method = RequestMethod.GET)
	 public String listLead(Model model) {
		   model.addAttribute("title", "Welcome salesforce SOAP API test");
		   try {
			LeadSoapService.ListLeads();;
		   } catch (ConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		   }
	       return "index";
	 }
}
