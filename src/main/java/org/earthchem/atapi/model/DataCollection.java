package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the data_collection database table.
 * 
 */
@Entity
@Table(name="data_collection")
@NamedQuery(name="DataCollection.findAll", query="SELECT d FROM DataCollection d")
public class DataCollection implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="collection_id")
	private Long collectionId;

	@Column(name="collection_form_url")
	private String collectionFormUrl;

	@Column(name="collection_name")
	private String collectionName;

	@Column(name="collection_url")
	private String collectionUrl;

	//bi-directional many-to-one association to Tracker
	@OneToMany(mappedBy="dataCollection")
	private List<Tracker> trackers;

	public DataCollection() {
	}

	public Long getCollectionId() {
		return this.collectionId;
	}

	public void setCollectionId(Long collectionId) {
		this.collectionId = collectionId;
	}

	public String getCollectionFormUrl() {
		return this.collectionFormUrl;
	}

	public void setCollectionFormUrl(String collectionFormUrl) {
		this.collectionFormUrl = collectionFormUrl;
	}

	public String getCollectionName() {
		return this.collectionName;
	}

	public void setCollectionName(String collectionName) {
		this.collectionName = collectionName;
	}

	public String getCollectionUrl() {
		return this.collectionUrl;
	}

	public void setCollectionUrl(String collectionUrl) {
		this.collectionUrl = collectionUrl;
	}

	public List<Tracker> getTrackers() {
		return this.trackers;
	}

	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}

	public Tracker addTracker(Tracker tracker) {
		getTrackers().add(tracker);
		tracker.setDataCollection(this);

		return tracker;
	}

	public Tracker removeTracker(Tracker tracker) {
		getTrackers().remove(tracker);
		tracker.setDataCollection(null);

		return tracker;
	}

}