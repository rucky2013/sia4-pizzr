package cc.picc.commons;

import org.apache.commons.lang3.StringUtils;

/**
 * 凭据类型
 * 
 * @author Justin
 *
 */
public enum CredentialType implements HasValue<String> {
	/**
	 * 文本密码
	 */
	PASSWORD("01");

	private String value;

	private CredentialType(String value) {
		this.value = value;
	}

	@Override
	public HasValue<String> fromValue(String value) {
		for (CredentialType type : values()) {
			if (StringUtils.equals(type.getValue(), value))
				return type;
		}
		throw new IllegalArgumentException("Invalid Input Value" + value);
	}

	@Override
	public String getValue() {
		return value;
	}
}
