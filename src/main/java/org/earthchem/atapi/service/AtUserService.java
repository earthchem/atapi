package org.earthchem.atapi.service;

import java.util.ArrayList;
import java.util.Optional;

import org.earthchem.atapi.model.AtUser;
import org.earthchem.atapi.repository.AtUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AtUserService {
	@Autowired
	AtUserRepository repo;
	
	/**
	 * Get all AtUser information from Table 'AtUser'.
	 * @return all table data in JSON format.
	 */
	public String getAllAtUserInfo()
	{
		Iterable<AtUser> allts = repo.findAll();
        ArrayList<AtUser> al = new ArrayList<AtUser>();
		for(AtUser t: allts)
		{
			al.add(t);
		}
		
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(al);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return e.toString();			
		}
	}
	
	/**
	 * Get a AtUser information from Table 'AtUser' according account_id.
	 * @return all table data in JSON format.
	 */
	public String getAtUserInfo(Long id)
	{
		Optional<AtUser> al = repo.findById(id);

		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		try {
			String jsonInString="Not Found";
			if(al.isPresent())
			{
			    jsonInString = mapper.writeValueAsString(al.get());
			}
			else
			{
				jsonInString = mapper.writeValueAsString(jsonInString);
			}
			return jsonInString;
		} catch (JsonProcessingException e) {

			return e.toString();			
		}
	}

	/**
	 * Insert a new user into at_user table if the user does not exists in the table
	 */
	public void addUser(Long id, String fname, String lname, String instname)
	{
		if(!repo.existsById(id))
		    repo.save(new AtUser(id, fname, lname, instname));
	}
	
	/**
	 * Delete an existing user into at_user table if the user does not exists in the table
	 */
	public void deleteUser(Long id)
	{
		if(repo.existsById(id))
		    repo.deleteById(id);;
	}
	
}
