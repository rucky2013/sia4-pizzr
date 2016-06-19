package cc.picc.commons;

import javax.persistence.AttributeConverter;

import org.thymeleaf.util.StringUtils;

/**
 * 
 * @author Justin
 *
 */
public class ContactTypeConverter implements AttributeConverter<ContactType, String> {

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
		for (ContactType t : ContactType.values()) {
			if (StringUtils.equalsIgnoreCase(t.getValue(), dbData)) {
				return t;
			}
		}
		throw new IllegalArgumentException("Invalid ContactType Value:" + dbData);
	}

}
