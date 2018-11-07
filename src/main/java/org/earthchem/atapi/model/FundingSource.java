package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the funding_source database table.
 * 
 */
@Entity
@Table(name="funding_source")
@NamedQuery(name="FundingSource.findAll", query="SELECT f FROM FundingSource f")
public class FundingSource implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="funding_source_id")
	private Integer fundingSourceId;

	private String description;

	private String name;

	//bi-directional many-to-one association to Tracker
	@OneToMany(mappedBy="fundingSource")
	private List<Tracker> trackers;

	public FundingSource() {
	}

	public Integer getFundingSourceId() {
		return this.fundingSourceId;
	}

	public void setFundingSourceId(Integer fundingSourceId) {
		this.fundingSourceId = fundingSourceId;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Tracker> getTrackers() {
		return this.trackers;
	}

	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}

	public Tracker addTracker(Tracker tracker) {
		getTrackers().add(tracker);
		tracker.setFundingSource(this);

		return tracker;
	}

	public Tracker removeTracker(Tracker tracker) {
		getTrackers().remove(tracker);
		tracker.setFundingSource(null);

		return tracker;
	}

}