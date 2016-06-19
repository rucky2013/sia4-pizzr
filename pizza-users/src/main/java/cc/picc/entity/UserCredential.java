package cc.picc.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.picc.commons.CredentialType;
import cc.picc.commons.CredentialTypeConverter;

@Entity
@Table(name = "uc_usercredential")
public class UserCredential {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "UserCredentialId")
	private Long id;

	/**
	 * 
	 */
	private Long userId;

	/**
	 * 
	 */
	@Convert(converter = CredentialTypeConverter.class)
	private CredentialType credentialType;

	private String content;

	private Date created;

	private Date latestModified;

	@Override
	public String toString() {
		return String.format(
				"UserCredential [id=%s, userId=%s, credentialType=%s, content=%s, created=%s, latestModified=%s]", id,
				userId, credentialType, content, created, latestModified);
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public CredentialType getCredentialType() {
		return credentialType;
	}

	public String getContent() {
		return content;
	}

	public Date getCreated() {
		return created;
	}

	public Date getLatestModified() {
		return latestModified;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public void setCredentialType(CredentialType credentialType) {
		this.credentialType = credentialType;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setLatestModified(Date latestModified) {
		this.latestModified = latestModified;
	}

}
