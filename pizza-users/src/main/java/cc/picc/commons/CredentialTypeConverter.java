package cc.picc.commons;

import javax.persistence.AttributeConverter;

import org.thymeleaf.util.StringUtils;

public class CredentialTypeConverter implements AttributeConverter<CredentialType, String> {
	@Override
	public String convertToDatabaseColumn(CredentialType attribute) {
		return attribute.getValue();
	}

	@Override
	public CredentialType convertToEntityAttribute(String dbData) {
		for (CredentialType t : CredentialType.values()) {
			if (StringUtils.equalsIgnoreCase(t.getValue(), dbData)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Invalid ContactType Value:" + dbData);
	}
}
