package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the at_users database table.
 * 
 */
@Entity
@Table(name="at_users")
@NamedQuery(name="AtUser.findAll", query="SELECT a FROM AtUser a")
public class AtUser implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="account_id")
	private Long accountId;

	@CreationTimestamp
	@Column(name="first_creation_time")	
	private Timestamp firstCreationTime;

	@Column(name="first_name")
	private String firstName;

	@Column(name="last_name")
	private String lastName;
	
	private String institution;

	//bi-directional many-to-one association to AuditLog
	@OneToMany(mappedBy="atUser")
	private List<AuditLog> auditLogs;

	//bi-directional many-to-one association to Tracker
	@OneToMany(mappedBy="atUser")
	private List<Tracker> trackers;

	public AtUser() {
	}

	public AtUser(Long account_id, String firstName, String lastName, String institution) {
		super();
		this.accountId = account_id;
		this.firstName = firstName;
		this.institution = institution;
		this.lastName = lastName;
	}

	public Long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(Long accountId) {
		this.accountId = accountId;
	}

	public Timestamp getFirstCreationTime() {
		return this.firstCreationTime;
	}

	public void setFirstCreationTime(Timestamp firstCreationTime) {
		this.firstCreationTime = firstCreationTime;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getInstitution() {
		return this.institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<AuditLog> getAuditLogs() {
		return this.auditLogs;
	}

	public void setAuditLogs(List<AuditLog> auditLogs) {
		this.auditLogs = auditLogs;
	}

	public AuditLog addAuditLog(AuditLog auditLog) {
		getAuditLogs().add(auditLog);
		auditLog.setAtUser(this);

		return auditLog;
	}

	public AuditLog removeAuditLog(AuditLog auditLog) {
		getAuditLogs().remove(auditLog);
		auditLog.setAtUser(null);

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
		tracker.setAtUser(this);

		return tracker;
	}

	public Tracker removeTracker(Tracker tracker) {
		getTrackers().remove(tracker);
		tracker.setAtUser(null);

		return tracker;
	}

	@Override
	public String toString() {
		return "AtUser [accountId=" + accountId + ", firstCreationTime=" + firstCreationTime + ", firstName="
				+ firstName + ", lastName=" + lastName + ", institution=" + institution + ", auditLogs=" + auditLogs
				+ "]";
	}

}