package cc.picc.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Justin
 *
 */
public enum ContactType implements HasValue<String> {

	/**
	 * 固话号码
	 */
	TELEPHONE("01"),
	/**
	 * 移动电话号码
	 */
	MOBILE_PHONE("02"),

	/**
	 * 邮件地址
	 */
	EMAIL_ADDRESS("03"),

	/**
	 * 居住地址
	 */
	GEOGRAPHICAL_ADDRESS("04"),

	/**
	 * QQ号码
	 */
	QQ_IDENTITY("05"),

	/**
	 * 微信号码
	 */
	WECHAT_IDENTITY("06");

	private String value;

	private ContactType(String value) {
		this.value = value;
	}

	@Override
	public String getValue() {
		return value;
	}

	@Override
	public HasValue<String> fromValue(String value) {
		for (ContactType type : values()) {
			if (StringUtils.equals(type.getValue(), value))
				return type;
		}
		throw new IllegalArgumentException("Invalid Input Value" + value);
	}
}