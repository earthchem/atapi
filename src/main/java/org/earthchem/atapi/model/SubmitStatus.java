package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the submit_status database table.
 * 
 */
@Entity
@Table(name="submit_status")
@NamedQuery(name="SubmitStatus.findAll", query="SELECT s FROM SubmitStatus s")
public class SubmitStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="submit_status_id")
	private Integer submitStatusId;

	@Column(name="submit_status_name")
	private String submitStatusName;

	//bi-directional many-to-one association to AuditLog
	@OneToMany(mappedBy="submitStatus")
	private List<AuditLog> auditLogs;

	//bi-directional many-to-one association to Tracker
	@OneToMany(mappedBy="submitStatusBean")
	private List<Tracker> trackers;

	public SubmitStatus() {
	}

	public SubmitStatus(Integer submitStatusId) {
		super();
		this.submitStatusId = submitStatusId;
	}

	public Integer getSubmitStatusId() {
		return this.submitStatusId;
	}

	public void setSubmitStatusId(Integer submitStatusId) {
		this.submitStatusId = submitStatusId;
	}

	public String getSubmitStatusName() {
		return this.submitStatusName;
	}

	public void setSubmitStatusName(String submitStatusName) {
		this.submitStatusName = submitStatusName;
	}

	public List<AuditLog> getAuditLogs() {
		return this.auditLogs;
	}

	public void setAuditLogs(List<AuditLog> auditLogs) {
		this.auditLogs = auditLogs;
	}

	public AuditLog addAuditLog(AuditLog auditLog) {
		getAuditLogs().add(auditLog);
		auditLog.setSubmitStatus(this);

		return auditLog;
	}

	public AuditLog removeAuditLog(AuditLog auditLog) {
		getAuditLogs().remove(auditLog);
		auditLog.setSubmitStatus(null);

		return auditLog;
	}

	public List<Tracker> getTrackers() {
		return this.trackers;
	}

	public void setTrackers(List<Tracker> trackers) {
		this.trackers = trackers;
	}

	public Tracker addTracker(Tracker tracker) {
		getTrackers().add(tracker);
		tracker.setSubmitStatusBean(this);

		return tracker;
	}

	public Tracker removeTracker(Tracker tracker) {
		getTrackers().remove(tracker);
		tracker.setSubmitStatusBean(null);

		return tracker;
	}

}