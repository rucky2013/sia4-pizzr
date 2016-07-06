package cc.pizzr.commons;

import javax.persistence.AttributeConverter;

/**
 * 
 * @author lijinting01
 * 
 */
public class CredentialTypeConverter implements
		AttributeConverter<CredentialType, String> {
	@Override
	public String convertToDatabaseColumn(CredentialType attribute) {
		return attribute.getValue();
	}

	@Override
	public CredentialType convertToEntityAttribute(String dbData) {
		return CredentialType.fromValue(dbData);
	}
}
