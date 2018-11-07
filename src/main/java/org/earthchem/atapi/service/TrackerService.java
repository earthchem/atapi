package org.earthchem.atapi.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Optional;

import org.earthchem.atapi.model.AtUser;
import org.earthchem.atapi.model.DataCollection;
import org.earthchem.atapi.model.FundingSource;
import org.earthchem.atapi.model.SubmitStatus;
import org.earthchem.atapi.model.Tracker;
import org.earthchem.atapi.repository.AtUserRepository;
import org.earthchem.atapi.repository.DataCollectionRepository;
import org.earthchem.atapi.repository.FundingSourceRepository;
import org.earthchem.atapi.repository.SubmitStatusRepository;
import org.earthchem.atapi.repository.TrackerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class TrackerService {
	
	@Autowired
	TrackerRepository repo;
	
	@Autowired
	AtUserRepository user_repo;
	
	@Autowired
	SubmitStatusRepository status_repo;

	@Autowired
	FundingSourceRepository fs_repo;

	@Autowired
	DataCollectionRepository dc_repo;
	
	/**
	 * Get all tracker information from Table 'tracker'.
	 * @return all table data in JSON format.
	 */
	public String getAllTrackerInfo()
	{
		Iterable<Tracker> allts = repo.findAll();
        ArrayList<Tracker> al = new ArrayList<Tracker>();
		for(Tracker t: allts)
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

    public String getCount(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId) {
    	    
		Long cnt = repo.getCountByFundingnumDatavalueUseridFundingsource(awardNum, dcId, dataValue, userId, awardSourceId);
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(cnt);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return e.toString();			
		}
    }
    
	/**
	 * Get a tracker information from Table 'tracker' according track_id.
	 * @return all table data in JSON format.
	 */
	public String getTrackerInfo(Long id)
	{
		Optional<Tracker> al = repo.findById(id);
    	ObjectMapper mapper = new ObjectMapper();
		Tracker t = al.get();

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(t);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return e.toString();			
		}
	}
    
	public String updateTrackerSubmitTime(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId
    		)
	{
		Integer cnt= repo.updateTrackerSubmitTime(awardNum, dcId, dataValue, userId, awardSourceId);
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(cnt);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return e.toString();			
		}
		
	}
    
	public void addNewTracker(
    		@Param("awardNum") String awardNum,
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("statusId") Integer statusId,
    		@Param("fsId")  Integer fsId
			)
	{
		Optional<AtUser> atUser = user_repo.findById(userId);
		Optional<DataCollection> dataCollection = dc_repo.findById(dcId);
		Optional<FundingSource> fundingSource = fs_repo.findById(fsId);
		Optional<SubmitStatus> st = status_repo.findById(statusId);
		
		Tracker t = new Tracker(
				                dataValue, 				                
				                awardNum, 				              
				                atUser.get(), 
				                dataCollection.get(), 
				                fundingSource.get(),
				                st.get()
				               );
		repo.save(t);
	}
	
	public String deleteTrackerByAwardnumDatavalue(
    		@Param("awardNum") String awardNum,
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue
    		)
    {
		Integer cnt= repo.deleteTrackerByAwardnumDatavalue(awardNum, dcId, dataValue);
		ObjectMapper mapper = new ObjectMapper();

		//Object to JSON in String
		try {
			String jsonInString = mapper.writeValueAsString(cnt);
			return jsonInString;
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			return e.toString();			
		}
    };
    
}
