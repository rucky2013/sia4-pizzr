package cc.pizzr.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import cc.pizzr.commons.ContactType;
import cc.pizzr.commons.ContactTypeConverter;

/**
 * 用户联系方式
 * 
 * @author Justin
 *
 */
@Entity
@Table(name = "UC_UserContactInfo")
public class UserContactInfo {
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "user_contact_id")
	private Long id;

	/**
	 * 联系方式对应的用户ID
	 */
	private Long userId;

	/**
	 * 联系方式类型
	 */
	@Convert(converter = ContactTypeConverter.class)
	private ContactType contactType;

	/**
	 * 联系方式内容
	 */
	private String payload;

	/**
	 * 有效状态
	 */
	private boolean enabled;

	/**
	 * 
	 */
	private Date created;

	private Date latestModified;

	@Override
	public String toString() {
		return String.format(
				"UserContactInfo [id=%s, userId=%s, contactType=%s, payload=%s, enabled=%s, created=%s, latestModified=%s]",
				id, userId, contactType, payload, enabled, created, latestModified);
	}

	public Long getId() {
		return id;
	}

	public Long getUserId() {
		return userId;
	}

	public ContactType getContactType() {
		return contactType;
	}

	public String getPayload() {
		return payload;
	}

	public boolean isEnabled() {
		return enabled;
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

	public void setContactType(ContactType contactType) {
		this.contactType = contactType;
	}

	public void setPayload(String payload) {
		this.payload = payload;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public void setLatestModified(Date latestModified) {
		this.latestModified = latestModified;
	}

}
