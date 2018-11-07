package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.sql.Timestamp;


/**
 * The persistent class for the tracker database table.
 * 
 */
@Entity
@NamedQuery(name="Tracker.findAll", query="SELECT t FROM Tracker t")
public class Tracker implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	//@SequenceGenerator(name="my_seq", sequenceName="tracker_track_id_seq")
	//@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="my_seq")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="track_id")
	private Long trackId;

	@Column(name="data_value")
	private String dataValue;

	@UpdateTimestamp
	@Column(name="last_status_time_change")
	private Timestamp lastStatusTimeChange;

	@Column(name="nsf_award_num")
	private String nsfAwardNum;

	@CreationTimestamp
	@Column(name="submitted_time")
	private Timestamp submittedTime;

	//bi-directional many-to-one association to AtUser
	@ManyToOne
	@JoinColumn(name="submitted_by_geopass_id")
	@JsonBackReference
	private AtUser atUser;

	//bi-directional many-to-one association to DataCollection
	@ManyToOne
	@JoinColumn(name="data_collection_id")
	@JsonBackReference
	private DataCollection dataCollection;

	//bi-directional many-to-one association to FundingSource
	@ManyToOne
	@JoinColumn(name="funding_source_id")
	@JsonBackReference
	private FundingSource fundingSource;

	//bi-directional many-to-one association to SubmitStatus
	@ManyToOne(optional = false)
	@JoinColumn(name="submit_status", updatable = true)
	@JsonBackReference
	private SubmitStatus submitStatusBean;

	public Tracker() {
		super();
	}

	public Tracker(String dataValue, 
			       String nsfAwardNum,
			       AtUser atUser,
			       DataCollection dataCollection,
			       FundingSource fundingSource,
			       SubmitStatus submitStatusBean
			      )
	{
		super();

		this.dataValue = dataValue;
		this.nsfAwardNum = nsfAwardNum;
		this.atUser = atUser;
		this.dataCollection = dataCollection;
		this.fundingSource = fundingSource;
		this.submitStatusBean = submitStatusBean;
	}

	public Long getTrackId() {
		return this.trackId;
	}

	public void setTrackId(Long trackId) {
		this.trackId = trackId;
	}

	public String getDataValue() {
		return this.dataValue;
	}

	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}

	public Timestamp getLastStatusTimeChange() {
		return this.lastStatusTimeChange;
	}

	public void setLastStatusTimeChange(Timestamp lastStatusTimeChange) {
		this.lastStatusTimeChange = lastStatusTimeChange;
	}

	public String getNsfAwardNum() {
		return this.nsfAwardNum;
	}

	public void setNsfAwardNum(String nsfAwardNum) {
		this.nsfAwardNum = nsfAwardNum;
	}

	public Timestamp getSubmittedTime() {
		return this.submittedTime;
	}

	public void setSubmittedTime(Timestamp submittedTime) {
		this.submittedTime = submittedTime;
	}

	public AtUser getAtUser() {
		return this.atUser;
	}

	public void setAtUser(AtUser atUser) {
		this.atUser = atUser;
	}

	public DataCollection getDataCollection() {
		return this.dataCollection;
	}

	public void setDataCollection(DataCollection dataCollection) {
		this.dataCollection = dataCollection;
	}

	public FundingSource getFundingSource() {
		return this.fundingSource;
	}

	public void setFundingSource(FundingSource fundingSource) {
		this.fundingSource = fundingSource;
	}

	public SubmitStatus getSubmitStatusBean() {
		return this.submitStatusBean;
	}

	public void setSubmitStatusBean(SubmitStatus submitStatusBean) {
		this.submitStatusBean = submitStatusBean;
	}

	@Override
	public String toString() {
		return "Tracker [trackId=" + trackId + ", dataValue=" + dataValue + ", lastStatusTimeChange="
				+ lastStatusTimeChange + ", nsfAwardNum=" + nsfAwardNum + ", submittedTime=" + submittedTime
				+ ", atUser=" + atUser + ", dataCollection=" + dataCollection + ", fundingSource=" + fundingSource
				+ ", submitStatusBean=" + submitStatusBean + "]";
	}


}