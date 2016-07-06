package cc.picc.commons;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cc.pizzr.commons.ContactType;
import cc.pizzr.commons.ContactTypeConverter;

/**
 * 
 * @author Justin
 *
 */
public class ContactTypeConverterTest {

	private ContactTypeConverter conv = new ContactTypeConverter();

	@Test
	public void testConvertToDatabaseColumn() {
		ContactType[] array = ContactType.values();

		for (ContactType c : array) {
			String expected = c.getValue();
			String actual = conv.convertToDatabaseColumn(c);
			assertThat(expected, is(equalTo(actual)));
		}
	}

	@Test
	public void testConvertToEntityAttribute() {
		for (ContactType c : ContactType.values()) {
			assertThat(c, is(equalTo(conv.convertToEntityAttribute(c.getValue()))));
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConvertToEntityAttributeWithIllegalArgument() {
		conv.convertToEntityAttribute("01023090910032");
	}
}
