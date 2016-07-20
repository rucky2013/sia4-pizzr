package cc.pizzr.commons;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import cc.pizzr.commons.CredentialType;
import cc.pizzr.commons.CredentialTypeConverter;

public class CredentialTypeConverterTest {

	private CredentialTypeConverter conv = new CredentialTypeConverter();

	@Test
	public void testConvertToDatabaseColumn() {
		CredentialType[] array = CredentialType.values();

		for (CredentialType c : array) {
			String expected = c.getValue();
			String actual = conv.convertToDatabaseColumn(c);
			assertThat(expected, is(equalTo(actual)));
		}
	}

	@Test
	public void testConvertToEntityAttribute() {
		assertThat(CredentialType.PASSWORD,
				is(equalTo(CredentialType.fromValue(CredentialType.PASSWORD
						.getValue()))));

	}

	@Test(expected = IllegalArgumentException.class)
	public void testConvertToEntityAttributeWithIllegalArgument() {
		conv.convertToEntityAttribute("01023090910032");
	}

}
