package cc.picc.commons;

import javax.persistence.AttributeConverter;

/**
 * 
 * @author Justin
 * 
 */
public class ContactTypeConverter implements
		AttributeConverter<ContactType, String> {

	/**
	 * 
	 */
	@Override
	public String convertToDatabaseColumn(ContactType attribute) {
		return attribute.getValue();
	}

	/**
	 * 
	 */
	@Override
	public ContactType convertToEntityAttribute(String dbData) {
		return ContactType.fromValue(dbData);
	}

}
