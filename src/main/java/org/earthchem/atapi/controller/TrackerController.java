package org.earthchem.atapi.controller;

import org.earthchem.atapi.repository.TrackerRepository;
import org.earthchem.atapi.service.TrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/tracker")
public class TrackerController {


	@Autowired
	TrackerRepository repository;
	
	@Autowired
    TrackerService ts;
	
	@RequestMapping(method = RequestMethod.GET, value = "/id", 
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String tracker(@RequestParam("id") Long id) {
    	    
		return ts.getTrackerInfo(id);
    }
    
	@RequestMapping(method = RequestMethod.GET, value = "/getCount",
			        produces = MediaType.APPLICATION_JSON_VALUE)
    public String getCount(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId) {
    	    
		return ts.getCount(awardNum, dcId, dataValue, userId, awardSourceId);
    }
	
	@RequestMapping(method = RequestMethod.POST, value = "/add",
	                produces = MediaType.APPLICATION_JSON_VALUE)
	public void addNewTracker(
    		@Param("awardNum") String awardNum,
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("statusId") Integer statusId,
    		@Param("fsId")  Integer fsId
			)
	{
		ts.addNewTracker(awardNum, dcId, dataValue, userId, statusId, fsId);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/updateStatus",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public Integer updateStatus(@RequestParam("id") String id) {   	    
		return repository.updateStatus(id);
    }

	@RequestMapping(method = RequestMethod.POST, value = "/updateSubmitTime",
                    produces = MediaType.APPLICATION_JSON_VALUE)
	public String updateTrackerSubmitTime(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId
    		)
	{
		return ts.updateTrackerSubmitTime(awardNum, dcId, dataValue, userId, awardSourceId);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete",
                    produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteTrackerByAwardnumDatavalue(
    		@Param("awardNum") String awardNum,
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue
    		)
    {
		return ts.deleteTrackerByAwardnumDatavalue(awardNum, dcId, dataValue);
    };

}
