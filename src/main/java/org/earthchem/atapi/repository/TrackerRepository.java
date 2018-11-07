package org.earthchem.atapi.repository;

import java.util.Optional;

import org.earthchem.atapi.model.Tracker;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TrackerRepository extends CrudRepository<Tracker, Long> {

	@Query("SELECT t.nsfAwardNum FROM Tracker t where t.trackId = :id") 
    public Optional<String> findFoundingSourceById(@Param("id") Long id);
     
    @Query("SELECT t.trackId FROM Tracker t where t.nsfAwardNum = :id") 
    public Optional<Long> findTrackerByFoundingId(@Param("id") String id);
      
    @Query("SELECT t FROM Tracker t where t.dataCollection.collectionId = ?1 AND t.dataValue = ?2")
    public Optional<Tracker> findByDataCollectionAndDataID(String dataCollection, String dataValue);
    
    @Query("SELECT count(*) from Tracker t where t.nsfAwardNum = ?1 AND t.dataCollection.collectionId= ?2  AND t.dataValue = ?3 AND t.atUser.accountId = ?4 AND t.fundingSource.fundingSourceId = ?5")
    public Long getCountByFundingnumDatavalueUseridFundingsource(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId
    		);
    
    /** Update submission status to public. 5 means 'published' */
    @Query("UPDATE Tracker t SET t.submitStatusBean.submitStatusId = '5', t.lastStatusTimeChange=now() WHERE t.dataValue = :id")
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    public Integer updateStatus(String id);
   
    /**
     * Update geopass_id, submitted_time in tracker table for specific funding number without submission_id assocciated. 
    */
    @Query("UPDATE Tracker t SET t.submittedTime =now() WHERE t.nsfAwardNum = ?1 AND t.dataCollection.collectionId = ?2 AND t.dataValue = ?3 AND t.atUser.accountId = ?4 AND t.fundingSource.fundingSourceId = ?5")
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    public Integer updateTrackerSubmitTime(
    		@Param("awardNum") String awardNum, 
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue,
    		@Param("userId") Long userId,
    		@Param("awardSourceId") Integer awardSourceId
    		);
    
    /**
     * Delete an entry in tracker table according to submission id, collection id and award number.
    */
    @Query("DELETE from Tracker t WHERE t.nsfAwardNum = ?1 AND t.dataCollection.collectionId = ?2 AND t.dataValue = ?3")
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    public Integer deleteTrackerByAwardnumDatavalue(
    		@Param("awardNum") String awardNum,
    		@Param("dcId") Long dcId,
    		@Param("dataValue") String dataValue
    		);
}