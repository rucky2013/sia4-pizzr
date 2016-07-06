package cc.pizzr.entity;

import static javax.persistence.GenerationType.AUTO;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 用户基本信息
 * 
 * @author Justin
 *
 */
@Table(name = "UC_UserBasics")
@Entity
public class UserBasics {
	/**
	 * 
	 */
	@Id
	@GeneratedValue(strategy = AUTO)
	@Column(name = "user_id")
	private Long id;

	/**
	 * 用户名称
	 */
	private String username;

	/**
	 * 用户的组织编码（例如HR编码）
	 */
	private String usercode;

	/**
	 * 用户的启用状态
	 */
	@Column(name = "Enabled")
	private boolean enabled;

	/**
	 * 用户的注册（创建）时间。
	 */
	private Date created;

	/**
	 * 
	 */
	private Date latestModified;

	public UserBasics() {
		this(null, null, false, null, null);
	}

	public UserBasics(String username, String usercode, boolean enabled, Date created, Date latestModified) {
		this.username = username;
		this.usercode = usercode;
		this.enabled = enabled;
		this.created = created;
		this.latestModified = latestModified;
	}

	public String toString() {
		return String.format("User [id=%s, username=%s, usercode=%s, enabled=%s, created=%s, latestModified=%s]", id,
				username, usercode, enabled, created, latestModified);
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getUsercode() {
		return usercode;
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

	public void setUsername(String username) {
		this.username = username;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
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
