package org.earthchem.atapi.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the audit_log database table.
 * 
 */
@Entity
@Table(name="audit_log")
@NamedQuery(name="AuditLog.findAll", query="SELECT a FROM AuditLog a")
public class AuditLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="audit_id")
	private Long auditId;

	@Column(name="last_modified")
	private Timestamp lastModified;

	//bi-directional many-to-one association to AtUser
	@ManyToOne
	@JoinColumn(name="account_id")
	private AtUser atUser;

	//bi-directional many-to-one association to SubmitStatus
	@ManyToOne
	@JoinColumn(name="submit_status_id")
	private SubmitStatus submitStatus;

	public AuditLog() {
	}

	public Long getAuditId() {
		return this.auditId;
	}

	public void setAuditId(Long auditId) {
		this.auditId = auditId;
	}

	public Timestamp getLastModified() {
		return this.lastModified;
	}

	public void setLastModified(Timestamp lastModified) {
		this.lastModified = lastModified;
	}

	public AtUser getAtUser() {
		return this.atUser;
	}

	public void setAtUser(AtUser atUser) {
		this.atUser = atUser;
	}

	public SubmitStatus getSubmitStatus() {
		return this.submitStatus;
	}

	public void setSubmitStatus(SubmitStatus submitStatus) {
		this.submitStatus = submitStatus;
	}

}