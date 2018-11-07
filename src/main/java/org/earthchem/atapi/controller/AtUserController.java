package org.earthchem.atapi.controller;

import org.earthchem.atapi.repository.AtUserRepository;
import org.earthchem.atapi.service.AtUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tracker")
public class AtUserController {

	private static Logger logger = LoggerFactory.getLogger("AtUserController");

	@Autowired
	AtUserRepository repository;
	
	@Autowired
    AtUserService ts;
	
	@RequestMapping(method = RequestMethod.GET, value = "/get", 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public String AtUser(@RequestParam("id") Long id) {
		return ts.getAtUserInfo(id);
    }
    
	@RequestMapping(method = RequestMethod.POST, value = "/addUser", 
    produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestParam("id") Long id,
    		@RequestParam("fname") String fname,
    		@RequestParam("lname") String lname,
    		@RequestParam("instname") String instname
    		) 
	
	{   	    
		ts.addUser(id, fname, lname, instname);
    }
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteUser", 
		    produces = MediaType.APPLICATION_JSON_VALUE)
    public void addUser(@RequestParam("id") Long id) 
	{   	    
	    ts.deleteUser(id);
    }
	
}
